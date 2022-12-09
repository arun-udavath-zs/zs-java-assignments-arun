package com.zs.assignment3.service;

import com.zs.assignment3.Product;
import com.zs.assignment3.ProductType;
import com.zs.assignment3.repository.ProductDatabase;

import java.util.Scanner;

public class Electronic implements Product {
    String productName;
    private final ProductDatabase productDatabase;

    public Electronic(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    @Override
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Electronic product name to add");
        productName = sc.next();
        productDatabase.addProduct(ProductType.ELECTRONIC, productName);
    }

    @Override
    public void readProduct() {
        productDatabase.readProduct(ProductType.ELECTRONIC);
    }

    @Override
    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        String prevProduct;
        System.out.println("Enter previous product name and new name to update");
        prevProduct = sc.next();
        productName = sc.next();
        productDatabase.updateProduct(ProductType.ELECTRONIC, prevProduct, productName);
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Electronic product name to delete");
        productName = sc.next();
        productDatabase.deleteProduct(ProductType.ELECTRONIC, productName);
    }
}
