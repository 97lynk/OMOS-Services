package com.kt3.orderservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    @Id
    @GeneratedValue
    private int id;

    private BigDecimal total;

    private Date createIn;

    private Date updateDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(){

    }

    public Order(BigDecimal total, Date createIn, Date updateDate, List<OrderItem> orderItems) {
        this.total = total;
        this.createIn = createIn;
        this.updateDate = updateDate;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
}

