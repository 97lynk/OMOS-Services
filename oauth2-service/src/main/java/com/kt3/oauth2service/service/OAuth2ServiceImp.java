/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.oauth2service.service;

import com.kt3.oauth2service.entity.*;
import com.kt3.oauth2service.respository.*;
import com.kt3.oauth2service.util.FineGrained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OAuth2ServiceImp implements OAuth2Service {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    FineGrained fineGrained;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public Account selectAccountByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public Role selectRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Privilege selectPrivilegeByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public Account insertAccount(Account account) {
        return accountRepository.save(account);
    }


}
