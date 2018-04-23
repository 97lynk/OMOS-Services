package com.kt3.menuservice.model;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String url;
    @ManyToOne
    private Product product;

    public Image(String url, Product product) {
        this.url = url;
        this.product = product;
    }
}
