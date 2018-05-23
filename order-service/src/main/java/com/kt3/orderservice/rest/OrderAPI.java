package com.kt3.orderservice.rest;

import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.OrderTable;
import com.kt3.orderservice.responsitory.AccountResponsitory;
import com.kt3.orderservice.responsitory.AddressResponsitory;
import com.kt3.orderservice.responsitory.OrderResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@PreAuthorize("#oauth2.hasScope('READ')")
public class OrderAPI {

    @Autowired
    private OrderResponsitory orderResponsitory;
    @Autowired
    private AccountResponsitory accountResponsitory;
    @Autowired
    private AddressResponsitory addressResponsitory;

    @GetMapping
    public ResponseEntity<List<OrderTable>> getAllOrders(OAuth2Authentication auth) {
        Account account = accountResponsitory.findAccountByUserName(auth.getName());
        List<OrderTable> orderTables = orderResponsitory.findAllByAddress_Account_id(account.getId());
        return ResponseEntity.ok(orderTables);
    }
}
