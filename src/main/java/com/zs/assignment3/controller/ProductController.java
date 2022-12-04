package com.zs.assignment3.controller;


import com.zs.assignment3.Product;
import com.zs.assignment3.repository.ProductDatabase;
import com.zs.assignment3.service.Electronic;
import com.zs.assignment3.service.Fashion;
import com.zs.assignment3.service.Grocery;
import com.zs.assignment3.service.Medicine;

import java.util.Scanner;

public class ProductController {
    public void controller() {
        Scanner sc = new Scanner(System.in);
        int productChoice;
        ProductDatabase productDatabase = new ProductDatabase();
        productDatabase.initialDatabase();
        Fashion fashion = new Fashion();
        Grocery grocery = new Grocery();
        Electronic electronic = new Electronic();
        Medicine medicine = new Medicine();

        System.out.println("choose the products of your choice press\n" +
                " 1. Fashions \n 2. Groceries\n 3. Electronics \n 4. Medicines");
        productChoice = sc.nextInt();

        switch (productChoice) {
            case 1:
                userOperation(fashion);
                break;
            case 2:
                userOperation(grocery);
                break;
            case 3:
                userOperation(electronic);
                break;
            case 4:
                userOperation(medicine);
                break;
        }

    }

    public void userOperation(Product product) {
        Scanner sc = new Scanner(System.in);
        System.out.println("press operations of your choice\n 1. add the product\n 2. to read the product \n " +
                "3. update the product\n 4. delete the product");
        int userChoice = sc.nextInt();

        switch (userChoice) {
            case 1:
                product.createProduct();
                break;
            case 2:
                product.readProduct();
                break;
            case 3:
                product.updateProduct();
                break;
            case 4:
                product.deleteProduct();
                break;
        }
    }

}