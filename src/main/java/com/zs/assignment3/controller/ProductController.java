package com.zs.assignment3.controller;


import com.zs.assignment3.Product;
import com.zs.assignment3.repository.ProductDatabase;
import com.zs.assignment3.service.Electronic;
import com.zs.assignment3.service.Fashion;
import com.zs.assignment3.service.Grocery;
import com.zs.assignment3.service.Medicine;

import java.util.Scanner;

public class ProductController {

    private final ProductDatabase productDatabase;
    private final Fashion fashion;
    private final Grocery grocery;
    private final Electronic electronic;
    private final Medicine medicine;

    public ProductController() {
        this.productDatabase = new ProductDatabase();
        this.fashion = new Fashion(productDatabase);
        this.grocery = new Grocery(productDatabase);
        this.electronic = new Electronic(productDatabase);
        this.medicine = new Medicine(productDatabase);
    }

    public void controller() {
        int productChoice;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("choose the products of your choice press\n" +
                    " 1. Fashions \n 2. Groceries\n 3. Electronics \n 4. Medicines \n 5. to exit");
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
                case 5:
                    break;
                default:
                    System.out.println("please choose the correct option");
                    controller();
            }
        }while (productChoice!= 5);

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
            default:
                System.out.println("please choice the correct option");
                userOperation(product);
        }
    }

}