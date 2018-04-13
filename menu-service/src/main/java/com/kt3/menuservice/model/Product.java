package com.kt3.menuservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private String thumbnail;
    //private List<String> moreImages;
    private BigDecimal price;
    @ManyToOne
    private Category category;
    private Date updateDate;
    private boolean status;
    private int quantity;
    private boolean hotFlag;

}
