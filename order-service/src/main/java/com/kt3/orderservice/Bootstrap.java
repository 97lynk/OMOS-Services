package com.kt3.orderservice;

import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.responsitory.AccountResponsitory;
import com.kt3.orderservice.responsitory.CartResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    CartResponsitory cartResponsitory;

    @Autowired
    AccountResponsitory accountResponsitory;

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account("tuan", "111");
        Cart cart = new Cart();
        cart.setTotalPrice(new BigDecimal(333.000));
        Account svedAcc = accountResponsitory.save(account);
        //Cart saved = cartResponsitory.save(cart);


        // List<Account> accountList = new ArrayList<>();
       // accountList.add(account);
//        accountResponsitory.save(account);
//        cartResponsitory.save(cart);
        svedAcc.setCart(cart);
        cart.setAccount(svedAcc);
        cartResponsitory.save(cart);


       accountResponsitory.save(svedAcc);
       //cartResponsitory.save(cart);
    }
}
