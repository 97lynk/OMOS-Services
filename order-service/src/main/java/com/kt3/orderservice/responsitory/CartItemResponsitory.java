package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemResponsitory extends JpaRepository<CartItem, Integer> {
}
