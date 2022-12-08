package com.zs.assignment3.service;

import com.zs.assignment3.Product;
import com.zs.assignment3.ProductType;
import com.zs.assignment3.repository.ProductDatabase;

import java.util.Scanner;

public class Medicine implements Product {
    String medicineName;
    private final ProductDatabase productDatabase;

    public Medicine(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    @Override
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Medicine  name to add");
        medicineName = sc.next();
        productDatabase.addProduct(ProductType.MEDICINE, medicineName);
    }

    @Override
    public void readProduct() {
        productDatabase.readProduct(ProductType.MEDICINE);
    }

    @Override
    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        String prevMedicine;
        System.out.println("Enter medicine name to search");
        prevMedicine = sc.next();
        medicineName = sc.next();
        productDatabase.updateProduct(ProductType.MEDICINE, prevMedicine, medicineName);
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter medicine name to delete");
        medicineName = sc.next();
        productDatabase.deleteProduct(ProductType.MEDICINE, medicineName);
    }
}
