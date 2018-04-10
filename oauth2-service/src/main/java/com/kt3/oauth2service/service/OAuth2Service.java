/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.oauth2service.service;


import com.kt3.oauth2service.entity.*;

/**
 *
 * @author 97lynk
 */
public interface OAuth2Service {

    Account selectAccountByUserName(String userName);

    Role selectRoleByName(String name);

    Privilege selectPrivilegeByName(String name);

    Account insertAccount(Account account);

}
