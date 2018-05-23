package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderResponsitory extends JpaRepository<OrderTable, Integer> {
    List<OrderTable> findAllByCode(String code);

    List<OrderTable> findAllByAddress_Account_id(int accountId);
}
