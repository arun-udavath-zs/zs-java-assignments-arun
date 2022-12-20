package com.zs.assignment11.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Category {
    @Id
    private int categoryId;
    private String name;

    Category() {
    }

    ;

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}
