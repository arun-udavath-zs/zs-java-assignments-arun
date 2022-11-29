package com.zs.assignment3;

import com.zs.assignment3.service.electronics.Electronic;
import com.zs.assignment3.service.electronics.ElectronicMain;
import com.zs.assignment3.service.electronics.Phones;
import com.zs.assignment3.service.electronics.Televisions;
import com.zs.assignment3.service.groceries.FoodItems;
import com.zs.assignment3.service.groceries.Groceries;
import com.zs.assignment3.service.groceries.GroceriesItem;
import com.zs.assignment3.service.groceries.Vegetables;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    public void product(){
        Scanner sc = new Scanner(System.in);
        System.out.println("press 1 for buy or 2 for add the product");
        int productItem= sc.nextInt();
        if(productItem == 1) {
            System.out.println("Enter 1 for electronics or 2 for groceries");
            int option = sc.nextInt();
             if (option == 1) {
                System.out.println("press 1 for mobiles or press 2 for televisions");
                int item = sc.nextInt();
                Electronic product;
                if (item == 1) {
                    System.out.println("Enter the brand of mobile like Apple or onePlus");
                    String phone = sc.next();
                    product = new Phones(phone);
                } else {
                    System.out.println("Enter the brand of television like lg or samsung");
                    String television = sc.next();
                    product = new Televisions(television);
                }
                ElectronicMain obj = new ElectronicMain(product);
                obj.filter();
            }else if (option == 2) {
                System.out.println("press 1 for FoodItems or press 2 for Vegetable");
                int item = sc.nextInt();
                GroceriesItem grocery;
                if (item == 1) {
                    System.out.println("Enter the foodItem like dal or rice");
                    String food_item = sc.next();
                    grocery = new FoodItems(food_item);
                } else {
                    System.out.println("Enter vegetables like tomato and potato");
                    String foodItem = sc.next();
                    grocery = new Vegetables(foodItem);
                }
                Groceries obj = new Groceries(grocery);
                obj.filter();
            }
        }
        else{
            List<String> products = new ArrayList<>();
            products.add("Electronics");
            products.add("Groceries");
            System.out.println("List of products are : "+products );
            System.out.println("press 1  to add a new Product or press 2");
            int product= sc.nextInt();
            if (product == 1){
                System.out.println("Enter the product");
                String newProduct = sc.next();
                products.add(newProduct);
                System.out.println("List of products are : "+ products);
            }
            else {
                List<String> electronics = new ArrayList<>();
                electronics.add("phones");
                electronics.add("televisions");
                List<String> groceries = new ArrayList<>();
                groceries.add("FoodItem");
                groceries.add("Vegetables");
                System.out.println("press 1 to display the electronics or 2 for groceries");
                int item= sc.nextInt();
                if(item == 1){
                    System.out.println("Item in electronics are : "+ electronics);
                    System.out.println("press 1  to add a new electronic or press 2");
                    int item1= sc.nextInt();
                    if (item1 == 1){
                        System.out.println("Enter the item");
                        String newItem= sc.next();
                        electronics.add(newItem);
                        System.out.println("List of products are : "+ electronics);
                    }
                }
                else {
                    System.out.println("Items in the groceries are :"+ groceries);
                    System.out.println("press 1  to add a new grocery or press 2");
                    int item1 = sc.nextInt();
                    if (item1 == 1){
                        System.out.println("Enter the item");
                        String newItem = sc.next();
                        groceries.add(newItem);
                        System.out.println("List of products are : "+ groceries);
                    }
                }
            }
        }
    }

}
