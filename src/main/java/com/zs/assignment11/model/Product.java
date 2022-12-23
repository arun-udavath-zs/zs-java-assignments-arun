package com.zs.assignment11.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product {
    @Id
    private Integer id;
    @JoinColumn(name = "product_name")
    private String productName;
    private int price;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}
    public Product(Integer id, String productName, int price, Category category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

}
