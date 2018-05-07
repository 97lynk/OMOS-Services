package com.kt3.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    private String thumbnail;

    private BigDecimal price;

    private Date updateDate;

    private boolean status;

    private int quantity;

    private boolean hotFlag;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String code, String description, String thumbnail, BigDecimal price, Date updateDate, boolean status, int quantity, boolean hotFlag) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.updateDate = updateDate;
        this.status = status;
        this.quantity = quantity;
        this.hotFlag = hotFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(boolean hotFlag) {
        this.hotFlag = hotFlag;
    }
}
