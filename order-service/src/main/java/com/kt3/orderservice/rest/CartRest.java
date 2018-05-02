package com.kt3.orderservice.rest;

import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.model.CartItem;
import com.kt3.orderservice.responsitory.AccountResponsitory;
import com.kt3.orderservice.responsitory.CartResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartRest {
//
//    @Autowired
//    private CartItemResponsitory cartItemResponsitory;

    @Autowired
    CartResponsitory cartResponsitory;

    @Autowired
    AccountResponsitory accountResponsitory;
//    @GetMapping
//    public List<CartItem> test() {
//     //   cartItemResponsitory.save(new CartItem(ICE_LEVEL.FIFTY_PERCENT, SUGAR_LEVEL.ONE_HUNDRED_PERCENT, 2, new BigDecimal(30.0)));
//        return cartItemResponsitory.findAll();
//    }

    @GetMapping("/acc")
    public List<Account> accounts(){
        return accountResponsitory.findAll();
    }

    @GetMapping("/cart")
    public List<Cart> carts(){
        return cartResponsitory.findAll();
    }

    // load cart

    // add to cart

    // remove item from card

    // change item

    // clear all item

    // submit cart => order (status: DA_NHAN, DA_HUY, DANG_GIAO_HANG, DA_GIAO) => bill
}

