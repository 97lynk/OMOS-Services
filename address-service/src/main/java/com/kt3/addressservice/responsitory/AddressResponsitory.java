package com.kt3.addressservice.responsitory;

import com.kt3.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressResponsitory extends JpaRepository<Address, Integer> {

    List<Address> findAddressesByAccount_id(int accountId);

}
