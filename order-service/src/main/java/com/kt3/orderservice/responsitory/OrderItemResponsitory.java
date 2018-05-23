package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemResponsitory extends JpaRepository<OrderItem, Integer> {
}
