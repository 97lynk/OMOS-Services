package com.kt3.addressservice.service;

import com.kt3.addressservice.model.Account;
import com.kt3.addressservice.model.Address;
import com.kt3.addressservice.responsitory.AccountResponsitory;
import com.kt3.addressservice.responsitory.AddressResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AccountResponsitory accountResponsitory;

    @Autowired
    private AddressResponsitory addressResponsitory;

    @Override
    public List<Address> selectAddresses() {
        return addressResponsitory.findAll();
    }

    @Override
    public Address selectAddressById(int id) {
        return addressResponsitory.findOne(id);
    }

    @Override
    public List<Address> selectAddressesByUsername(String userName) throws NoSuchElementException {
        Account account = accountResponsitory.findAccountByUserName(userName);
        if (account == null)
            throw new NoSuchElementException("This account is not exist");
        return addressResponsitory.findAddressesByAccount_id(account.getId()).stream()
                .filter(Address::isEnable).collect(Collectors.toList());
    }


    @Override
    public Address insertAddress(Address address) {
        address.setEnable(true);
        return addressResponsitory.save(address);
    }

    @Override
    public Address updateAddress(Address address) throws NoSuchElementException {
        if (!addressResponsitory.exists(address.getId()))
            throw new NoSuchElementException("This address is not exist");

        Address oldAddress = addressResponsitory.findOne(address.getId());

        oldAddress.setEnable(true);
        oldAddress.setFullName(address.getFullName());
        oldAddress.setPhone(address.getPhone());
        oldAddress.setAddress(address.getAddress());
        oldAddress.setWardID(address.getWardID());
        oldAddress.setWardName(address.getWardName());
        oldAddress.setProvinceID(address.getProvinceID());
        oldAddress.setProvinceName(address.getProvinceName());
        oldAddress.setCityID(address.getCityID());
        oldAddress.setCityName(address.getCityName());

        return addressResponsitory.save(oldAddress);
    }

    @Override
    public void deleleAddress(int id) throws NoSuchElementException {
        if (!addressResponsitory.exists(id))
            throw new NoSuchElementException("This address is not exist");
        Address address = addressResponsitory.findOne(id);
        address.setEnable(false);
        addressResponsitory.save(address);
    }

    @Override
    public Account selectAccountByAuth(OAuth2Authentication auth) {
        return accountResponsitory.findAccountByUserName(auth.getName());
    }
}
