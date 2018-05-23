package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressResponsitory extends JpaRepository<Address, Integer> {
    List<Address> findAllByAccount_id(int accountId);
}

