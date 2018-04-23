package com.kt3.addressservice.responsitory;

import com.kt3.addressservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountResponsitory extends JpaRepository<Account, Integer> {

    Account findAccountByUserName(String userName);

}
