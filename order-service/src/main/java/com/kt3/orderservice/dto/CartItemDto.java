package com.kt3.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kt3.orderservice.contanst.ICE_LEVEL;
import com.kt3.orderservice.contanst.SUGAR_LEVEL;
import com.kt3.orderservice.model.Cart;
import com.kt3.orderservice.model.Product;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class CartItemDto{

    private ICE_LEVEL iceLevel;

    private SUGAR_LEVEL sugarLevel;

    private int quantity;

    private BigDecimal subTotal;

    private int productId;

    public CartItemDto() {
    }

    public ICE_LEVEL getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(ICE_LEVEL iceLevel) {
        this.iceLevel = iceLevel;
    }

    public SUGAR_LEVEL getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(SUGAR_LEVEL sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return iceLevel + " " + sugarLevel + " " + productId;
    }
}
