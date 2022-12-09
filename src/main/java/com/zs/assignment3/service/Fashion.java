package com.zs.assignment3.service;

import com.zs.assignment3.Product;
import com.zs.assignment3.ProductType;
import com.zs.assignment3.repository.ProductDatabase;

import java.util.Scanner;

public class Fashion implements Product {

    String productName;
    ProductDatabase productDatabase;

    public Fashion(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    @Override
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name to add");
        productName = sc.next();
        productDatabase.addProduct(ProductType.FASHION, productName);
    }

    @Override
    public void readProduct() {
        productDatabase.readProduct(ProductType.FASHION);
    }

    @Override
    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        String prevProduct;
        System.out.println("Enter previous product name and new product name to update");
        prevProduct = sc.next();
        productName = sc.next();
        productDatabase.updateProduct(ProductType.FASHION, prevProduct, productName);
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name to delete");
        productName = sc.next();
        productDatabase.deleteProduct(ProductType.FASHION, productName);
    }
}