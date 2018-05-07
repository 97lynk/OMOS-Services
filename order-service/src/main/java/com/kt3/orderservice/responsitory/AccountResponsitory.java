package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountResponsitory extends JpaRepository<Account, Integer> {
    Account findAccountByUserName(String userName);
}

