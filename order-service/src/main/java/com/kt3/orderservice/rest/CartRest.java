package com.kt3.orderservice.rest;

import com.kt3.orderservice.dto.CartItemDto;
import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.model.CartItem;
import com.kt3.orderservice.model.Product;
import com.kt3.orderservice.responsitory.AccountResponsitory;
import com.kt3.orderservice.responsitory.CartItemResponsitory;
import com.kt3.orderservice.responsitory.CartResponsitory;
import com.kt3.orderservice.responsitory.ProductResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class CartRest {

    @Autowired
    CartResponsitory cartResponsitory;

    @Autowired
    AccountResponsitory accountResponsitory;

    @Autowired
    CartItemResponsitory cartItemResponsitory;

    @Autowired
    ProductResponsitory productResponsitory;

    private static final Logger logger = Logger.getLogger(CartRest.class.getName());

    // load a cart
    @PreAuthorize("#oauth2.hasScope('READ')")
    @GetMapping("/")
    public ResponseEntity<Cart> getCart(OAuth2Authentication auth) {
        logger.info("Username " + auth.getName());
        Account account = accountResponsitory.findAccountByUserName(auth.getName());
        return ResponseEntity.ok(Optional.of(account.getCart()).orElse(new Cart()));
    }

    // load list item in cart
    @PreAuthorize("#oauth2.hasScope('READ')")
    @GetMapping("/c")
    public ResponseEntity<List<CartItem>> getItemsInCart(OAuth2Authentication auth) {
        logger.info("Username " + auth.getName());
        Account account = accountResponsitory.findAccountByUserName(auth.getName());
        return ResponseEntity.ok(account.getCart().getCartItems());
    }

    // add item to cart
//    {
//        "iceLevel":"FIFTY_PERCENT",
//        "sugarLevel":"FIFTY_PERCENT",
//        "quantity":2,
//        "subTotal":2222.2,
//        "productId":2
//    }

    @PostMapping("/")
    @PreAuthorize("#oauth2.hasScope('READ')")
    public CartItem addItemToCart(@RequestBody CartItemDto cartItemDto, OAuth2Authentication auth) {
        CartItem cartItem = new CartItem(cartItemDto.getIceLevel(), cartItemDto.getSugarLevel(), cartItemDto.getQuantity(), cartItemDto.getSubTotal());

        Cart cart = accountResponsitory.findAccountByUserName(auth.getName()).getCart();

        Product product = productResponsitory.findOne((long) cartItemDto.getProductId());


        cartItem.setProduct(product);
        cartItem.setCart(cart);
        return cartItemResponsitory.save(cartItem);
    }

    // remove item from card
    @DeleteMapping("/")
    @PreAuthorize("#oauth2.hasScope('READ')")
    public void removeItemFromCart() {

    }
    // change item

    // clear all item

    // submit cart => order (status: DA_NHAN, DA_HUY, DANG_GIAO_HANG, DA_GIAO) => bill
}

