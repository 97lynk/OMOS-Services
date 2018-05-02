package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.Account;
import com.kt3.orderservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartResponsitory extends JpaRepository<Cart, Integer> {
}
