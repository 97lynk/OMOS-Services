package com.kt3.orderservice.responsitory;

import com.kt3.orderservice.contanst.ICE_LEVEL;
import com.kt3.orderservice.contanst.SUGAR_LEVEL;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemResponsitory extends JpaRepository<CartItem, Integer> {
    void deleteCartItemsByCart(Cart cart);

    CartItem findByCartIdAndProductIdAndIceLevelAndSugarLevel(int cartId, long productId, ICE_LEVEL ice_level, SUGAR_LEVEL sugar_level);

    boolean existsCartItemByCartIdAndProductIdAndIceLevelAndSugarLevel(int cartId, long productId, ICE_LEVEL ice_level, SUGAR_LEVEL sugar_level);

    List<CartItem> findAllByCartId(int cartId);
}
