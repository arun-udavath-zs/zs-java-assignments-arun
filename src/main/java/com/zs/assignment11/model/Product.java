package com.zs.assignment11.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Product {
    @Id
    private int id;
    private String productName;
    private int price;
    private int categoryId;

    Product() {
    }

    public Product(int id, String productName, int price, int categoryId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }

}
