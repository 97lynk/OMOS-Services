package com.kt3.orderservice.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private int id;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

//    @OneToOne(mappedBy = "cart")
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private Account account;

    public Cart() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
//
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
}
