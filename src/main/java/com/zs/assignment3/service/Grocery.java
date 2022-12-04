package com.zs.assignment3.service;

import com.zs.assignment3.Product;
import com.zs.assignment3.repository.ProductDatabase;
import com.zs.assignment3.ProductType;

import java.util.Scanner;

public class Grocery implements Product {
    String productName;
    ProductDatabase productDatabase = new ProductDatabase();

    @Override
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Grocery product name to add");
        productName = sc.next();
        productDatabase.addProduct(ProductType.GROCERY, productName);
    }

    @Override
    public void readProduct() {
        productDatabase.readProduct(ProductType.GROCERY);
    }

    @Override
    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        String prevProduct;
        System.out.println("Enter Grocery product name to delete");
        prevProduct = sc.next();
        productName = sc.next();
        productDatabase.updateProduct(ProductType.GROCERY, prevProduct, productName);
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Grocery product name to delete");
        productName = sc.next();
        productDatabase.deleteProduct(ProductType.GROCERY, productName);

    }
}
