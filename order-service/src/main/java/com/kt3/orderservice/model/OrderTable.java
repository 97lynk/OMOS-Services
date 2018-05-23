package com.kt3.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kt3.orderservice.contanst.ORDER_STATUS;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal totalPrice;

    private Date createIn;

    private Date updateDate;

    private String code;

    @Enumerated
    private ORDER_STATUS order_status;

    @JsonIgnore
    @OneToMany(mappedBy = "orderTable")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public OrderTable(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateIn() {
        return createIn;
    }

    public void setCreateIn(Date createIn) {
        this.createIn = createIn;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public ORDER_STATUS getOrder_status() {
        return order_status;
    }

    public void setOrder_status(ORDER_STATUS order_status) {
        this.order_status = order_status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

