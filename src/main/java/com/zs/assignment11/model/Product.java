package com.zs.assignment11.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Product {
    @Id
    private int id;
    private String productName;

    public Product(int id, String productName, String category, int price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }
    Product(){}
    private String category;
    private int price;
}
