package com.kt3.addressservice.service;

import com.kt3.addressservice.model.Account;
import com.kt3.addressservice.model.Address;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;
import java.util.NoSuchElementException;

public interface AddressService {

    List<Address> selectAddresses();

    Address selectAddressById(int id);

    List<Address> selectAddressesByUsername(String userName) throws NoSuchElementException;

    Address insertAddress(Address address);

    Address updateAddress(Address address) throws NoSuchElementException;

    void deleleAddress(int id) throws NoSuchElementException;

    Account selectAccountByAuth(OAuth2Authentication auth);
}

