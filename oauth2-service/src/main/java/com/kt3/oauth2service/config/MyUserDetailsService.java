/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.oauth2service.config;

import com.kt3.oauth2service.entity.Account;
import com.kt3.oauth2service.service.OAuth2ServiceImp;
import com.kt3.oauth2service.util.FineGrained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    OAuth2ServiceImp service;

    @Autowired
    private FineGrained fineGrained;
    
    private static final Logger logger
            = Logger.getLogger(MyUserDetailsService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        logger.info("Via UserDetailService");
        Account account = service.getAccountByUserName(userName);
        if(account == null)
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        logger.info(account.toString());
        
        fineGrained.getAuthorities(account.getRoles()).forEach(System.out::println);
        return new User(
                account.getUserName(), account.getPassword(), account.isEnabled(),
                true, true,
                true, fineGrained.getAuthorities(account.getRoles()));
    }

    
}
