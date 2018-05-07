package com.kt3.orderservice;

import com.kt3.orderservice.contanst.ICE_LEVEL;
import com.kt3.orderservice.contanst.SUGAR_LEVEL;
import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.model.CartItem;
import com.kt3.orderservice.model.Product;
import com.kt3.orderservice.responsitory.AccountResponsitory;
import com.kt3.orderservice.responsitory.CartItemResponsitory;
import com.kt3.orderservice.responsitory.CartResponsitory;
import com.kt3.orderservice.responsitory.ProductResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    CartResponsitory cartResponsitory;

    @Autowired
    AccountResponsitory accountResponsitory;

    @Autowired
    CartItemResponsitory cartItemResponsitory;

    @Autowired
    ProductResponsitory productResponsitory;

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account("tuan", "111");
        Cart cart = new Cart();
        cart.setTotalPrice(new BigDecimal(333.000));
        account.setCart(cart);

        accountResponsitory.save(account);

        Product product = new Product("a", "b", "c", "d",
                new BigDecimal(222.22), Date.valueOf(LocalDate.now()), true, 111, false);

        Product product2 = new Product("a2", "b2", "c", "d",
                new BigDecimal(222.22), Date.valueOf(LocalDate.now()), true, 111, false);

        productResponsitory.save(product);
        productResponsitory.save(product2);

        CartItem cartItem = new CartItem(ICE_LEVEL.FIFTY_PERCENT, SUGAR_LEVEL.ONE_HUNDRED_PERCENT, 2, new BigDecimal(2222.2));
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        CartItem cartItem2 = new CartItem(ICE_LEVEL.FIFTY_PERCENT, SUGAR_LEVEL.FIFTY_PERCENT, 2, new BigDecimal(2222.2));
        cartItem2.setProduct(product2);
        cartItem2.setCart(cart);
        cartItemResponsitory.save(cartItem);
        cartItemResponsitory.save(cartItem2);
    }


}
