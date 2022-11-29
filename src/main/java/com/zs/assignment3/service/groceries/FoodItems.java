package com.zs.assignment3.service.groceries;

import java.util.Scanner;

import static com.zs.assignment3.service.electronics.Phones.productBought;
public class FoodItems implements GroceriesItem{
    String foodItem;

    public FoodItems(String foodItem) {
        this.foodItem = foodItem;
    }
  // to filter the food items
    public int filter() {
        Scanner sc = new Scanner(System.in);

        switch (foodItem) {
            case "dal":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                int weight = sc.nextInt();
                if (weight == 1) {
                    productBought = "1kg of dal";
                    return 80;
                } else if (weight == 2) {
                    productBought = "5kg of dal";
                    return 400;
                }
                break;
            case "rice":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                int item = sc.nextInt();
                if (item == 1) {
                    productBought = "1kg of rice";
                    return 80;
                } else if (item == 2) {
                    productBought = "5kg of rice";
                    return 400;
                }
                break;
        }
        return 0;
    }
}
