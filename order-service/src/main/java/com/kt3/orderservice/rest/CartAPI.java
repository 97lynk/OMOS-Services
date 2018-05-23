package com.kt3.orderservice.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt3.orderservice.contanst.ORDER_STATUS;
import com.kt3.orderservice.dto.CartItemDto;
import com.kt3.orderservice.model.*;
import com.kt3.orderservice.responsitory.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@PreAuthorize("#oauth2.hasScope('READ')")
public class CartAPI {

    @Autowired
    CartResponsitory cartResponsitory;

    @Autowired
    AccountResponsitory accountResponsitory;

    @Autowired
    CartItemResponsitory cartItemResponsitory;

    @Autowired
    ProductResponsitory productResponsitory;

    @Autowired
    OrderResponsitory orderResponsitory;

    @Autowired
    OrderItemResponsitory orderItemResponsitory;

    @Autowired
    AddressResponsitory addressResponsitory;

    AbstractMap.SimpleEntry successMessage = new AbstractMap.SimpleEntry<>("message", "success");

    private static final Logger logger = Logger.getLogger(CartAPI.class.getName());

    // load a cart
    @GetMapping
    public ResponseEntity<Cart> getCart(OAuth2Authentication auth) {
        logger.info("Username " + auth.getName());
        Cart cart = loadCartByAuth(auth);
        return ResponseEntity.ok(cart);
    }


    // load list item in cart
    @GetMapping("/item")
    public ResponseEntity<List<CartItem>> getItemsInCart(OAuth2Authentication auth) {
        logger.info("Username " + auth.getName());
        Cart cart = loadCartByAuth(auth);
        return ResponseEntity.ok(cart.getCartItems());
    }

    // add item to cart
//    {
//        "iceLevel":"FIFTY_PERCENT",
//        "sugarLevel":"FIFTY_PERCENT",
//        "quantity":2,
//        "subTotal":2222.2,
//        "productId":2
//    }
    @PostMapping("/item")
    @Transactional
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemDto cartItemDto, OAuth2Authentication auth) {
        Cart cart = loadCartByAuth(auth);

        boolean alreadyExits = cartItemResponsitory.existsCartItemByCartIdAndProductIdAndIceLevelAndSugarLevel(
                cart.getId(), cartItemDto.getProductId(),
                cartItemDto.getIceLevel(), cartItemDto.getSugarLevel());
        logger.info("item " + cartItemDto.getQuantity());
        logger.info("exist " + alreadyExits);
        // kiểm tra item có trùng
        if (alreadyExits) {
            BigDecimal newSubtotal = calculateSubtotal(cartItemDto.getProductId(), cartItemDto.getQuantity());
            CartItem oldCartItem = cartItemResponsitory.findByCartIdAndProductIdAndIceLevelAndSugarLevel(
                    cart.getId(), cartItemDto.getProductId(), cartItemDto.getIceLevel(), cartItemDto.getSugarLevel());
            oldCartItem.setQuantity(oldCartItem.getQuantity() + cartItemDto.getQuantity());
            oldCartItem.setSubTotal(oldCartItem.getSubTotal().add(newSubtotal));
            cartItemResponsitory.save(oldCartItem);
        } else {
            CartItem cartItem = new CartItem();
            Product product = productResponsitory.findOne((long) cartItemDto.getProductId());
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setIceLevel(cartItemDto.getIceLevel());
            cartItem.setSugarLevel(cartItemDto.getSugarLevel());
            cartItem.setSubTotal(calculateSubtotal(cartItemDto.getProductId(), cartItemDto.getQuantity()));
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemResponsitory.save(cartItem);
        }
        updateTotalPrice(auth);
        return ResponseEntity.ok(successMessage);
    }

    // remove item from card
    @DeleteMapping("/item/{id}")
    @Transactional
    public ResponseEntity<?> removeItemFromCart(@PathVariable("id") int itemId, OAuth2Authentication auth) {
        logger.info("here");
        if (loadCartByAuth(auth).getCartItems().stream()
                .anyMatch(i -> i.getId() == itemId)) {
            cartItemResponsitory.delete(itemId);
            return ResponseEntity.ok(successMessage);
        }
        updateTotalPrice(auth);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no ok");
    }

    // change item
//    {
//        "iceLevel":"FIFTY_PERCENT",
//        "sugarLevel":"FIFTY_PERCENT",
//        "quantity":2,
//        "subTotal":2222.2,
//        "productId":2
//    }
    @PutMapping("/item/{id}")
    @Transactional
    public ResponseEntity<?> changeItemInCart(
            @PathVariable("id") int itemId, @RequestBody CartItemDto cartItemDto, OAuth2Authentication auth) {
        if (cartItemResponsitory.exists(itemId) &&
                loadCartByAuth(auth).getId() == cartItemResponsitory.findOne(itemId).getCart().getId()) {
            CartItem cartItem = cartItemResponsitory.findOne(itemId);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setIceLevel(cartItemDto.getIceLevel());
            cartItem.setSugarLevel(cartItemDto.getSugarLevel());
            cartItem.setSubTotal(calculateSubtotal(cartItemDto.getProductId(), cartItemDto.getQuantity()));
            cartItem.setProduct(productResponsitory.findOne((long) cartItemDto.getProductId()));
            cartItemResponsitory.save(cartItem);
        }

        updateTotalPrice(auth);
        return ResponseEntity.ok(successMessage);
    }

    // clear all item
    @DeleteMapping("/item")
    @Transactional
    public ResponseEntity<?> clearItemsInCart(OAuth2Authentication auth) {
        cartItemResponsitory.deleteCartItemsByCart(loadCartByAuth(auth));
        logger.info("deleted item");

        updateTotalPrice(auth);
        return ResponseEntity.ok(successMessage);
    }

    // submit cart => order (status: CHUA_XAC_NHAN, DA_NHAN, DA_HUY, DANG_GIAO_HANG, DA_GIAO) => bill
    @PostMapping
    @Transactional
    public ResponseEntity<?> submitCart(@RequestParam int addressId, OAuth2Authentication auth) {
        Address address = addressResponsitory.findOne(addressId);
        if (address == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AbstractMap.SimpleEntry<>("message", "address is not exist"));
        Cart cart = loadCartByAuth(auth);

        OrderTable savedOrder = moveCartToOrder(address, cart);
        clearItemsInCart(auth);


        String resBody = sendOTP(address.getPhone());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> res = new HashMap<>();
        try {
            JsonNode root = objectMapper.readTree(resBody);
            String status = root.get("status").asText();
            if (status.contains("success")) {
                String code = root.get("data").get("pin_code").asText();
                savedOrder.setCode(code);
                savedOrder = orderResponsitory.save(savedOrder);
                res.put("status", status);
                res.put("message", "Đã đặt hàng vui lòng xác nhận.");
                res.put("data", savedOrder);
                return ResponseEntity.ok(res);
            } else {
                res.put("status", status);
                res.put("message", root.get("message").asText());
                return ResponseEntity.ok(res);
            }
        } catch (IOException e) {
            res.put("status", "error");
            res.put("message", "Lỗi");
            return ResponseEntity.ok(res);
        }
    }


    @PostMapping("/submit")
    @Transactional
    public ResponseEntity<?> submitCode(@RequestParam String code, @RequestParam Integer orderId, OAuth2Authentication auth) {
        HashMap<String, String> res = new HashMap<>();
        try {
            OrderTable order = orderResponsitory.findOne(orderId);
            String resBody = confirmOTP(order.getAddress().getPhone(), code);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode data = objectMapper.readTree(resBody).get("data");
            boolean verified = data.get("verified").asBoolean();
            int remainingAttempts = data.get("remainingAttempts").asInt();
            String message = "";
            String status = "";
            if (verified) {
                status = "success";
                message = "Đã xác nhận đơn hàng thành công!";
                order.setOrder_status(ORDER_STATUS.DA_NHAN);
                orderResponsitory.save(order);
            } else {
                status = "invalid";
                if (remainingAttempts > 0)
                    message = "Mã xác nhận sai, bạn còn " + remainingAttempts + " lần thử.";
                else
                    message = "Mã xác nhận sai, vui lòng gửi lại mã khác!";
            }
            res.put("status", status);
            res.put("message", message);
            return ResponseEntity.ok(res);
        } catch (IOException e) {
            res.put("status", "error");
            res.put("message", "Lỗi");
            return ResponseEntity.ok(res);
        }
    }

    //chuyển cart sang order
    private OrderTable moveCartToOrder(Address address, Cart cart) {
        OrderTable newOrder = new OrderTable();
        newOrder.setAddress(address);
        newOrder.setCreateIn(Calendar.getInstance().getTime());
        newOrder.setUpdateDate(Calendar.getInstance().getTime());
        newOrder.setOrder_status(ORDER_STATUS.CHUA_XAC_NHAN);
        newOrder.setTotalPrice(cart.getTotalPrice());
        newOrder.setCode("");

        OrderTable savedOrder = orderResponsitory.save(newOrder);
        List<OrderItem> orderItems = cartItemResponsitory.findAllByCartId(cart.getId()).stream().map(ci -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setIceLevel(ci.getIceLevel());
            orderItem.setSugarLevel(ci.getSugarLevel());
            orderItem.setOrderTable(savedOrder);
            orderItem.setProduct(ci.getProduct());
            orderItem.setQuantity(ci.getQuantity());
            orderItem.setSubTotal(ci.getSubTotal());
            return orderItem;
        }).collect(Collectors.toList());

        orderItemResponsitory.save(orderItems);

        return savedOrder;
    }

    // tìm giỏ hàng theo Auth
    private Cart loadCartByAuth(OAuth2Authentication oauth2) {
        Account account = accountResponsitory.findAccountByUserName(oauth2.getName());
        Cart cart = account.getCart();
        if(cart == null){
            cart = new Cart();
            cart.setTotalPrice(new BigDecimal(0.0));
            account.setCart(cartResponsitory.save(cart));
            accountResponsitory.save(account);
        }
        return  cart;
    }

    // tính tiền 1 item
    private BigDecimal calculateSubtotal(int productId, int quantity) {
        logger.info("prodectId: " + productId);
        return productResponsitory.findOne((long) productId).getPrice().multiply(new BigDecimal(quantity));
    }

    //update tổng tiền của giỏ hàng
    private void updateTotalPrice(OAuth2Authentication oauth2) {
        Cart cart = loadCartByAuth(oauth2);
        Double newTotalPrice = cartItemResponsitory.findAllByCartId(cart.getId()).stream().map(CartItem::getSubTotal)
                .mapToDouble(BigDecimal::doubleValue).sum();
        cart.setTotalPrice(new BigDecimal(newTotalPrice));
        cartResponsitory.save(cart);
        logger.info("update total = " + newTotalPrice);
    }


    private String sendOTP(String phone) {
        String body = String.format("{\"to\": \"%s\", \"content\": \"%s\", \"app_id\": \"%s\"}",
                phone, "Ma xac nhan don hang cua ban la {pin_code}. Ma co hieu luc trong 120s.", "jXt5UkwQddUWlVD0eYkSdqhDSDTH5yjx");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange
                ("https://api.speedsms.vn/index.php/pin/create", HttpMethod.POST,
                        new HttpEntity<>(body, createHeaders("Rcotne4WpNsxH9jqt_J_jNEXCueRVRKY", "x")), String.class);
        logger.info("send: " + body);
        logger.info("receive: " + res.getBody().toString());
        return res.getBody().toString();
    }

    private String resendOTP(String phone) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = sendOTP(phone);

        if (res.contains("error")) {
            return objectMapper.readTree(res).get("message").asText();
        }
        return "Vui lòng kiểm tra lại tin nhắn!";
    }

    private String confirmOTP(String phone, String code) throws IOException {
        String body = String.format("{\"phone\": \"%s\", \"app_id\": \"%s\", \"pin_code\": \"%s\"}",
                phone, "jXt5UkwQddUWlVD0eYkSdqhDSDTH5yjx", code);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange
                ("https://api.speedsms.vn/index.php/pin/verify", HttpMethod.POST,
                        new HttpEntity<>(body, createHeaders("Rcotne4WpNsxH9jqt_J_jNEXCueRVRKY", "x")), String.class);
        System.out.println(res.getBody().toString());
        logger.info(res.getBody().toString());
        return res.getBody().toString();
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}

