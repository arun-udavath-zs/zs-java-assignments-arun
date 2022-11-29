package com.zs.assignment3.service.groceries;

import java.util.Scanner;
import static com.zs.assignment3.service.electronics.Phones.productBought;

public class FoodItems implements GroceriesItem {
    String foodItem;
    public FoodItems(String foodItem) {
        this.foodItem = foodItem;
    }
    // to filter the food items
    public int filter() {
        Scanner sc = new Scanner(System.in);
        int dalWeight, riceWeight;

        switch (foodItem) {
            case "dal":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                dalWeight = sc.nextInt();
                if (dalWeight == 1) {
                    productBought = "1kg of dal";
                    return 80;
                } else if (dalWeight == 2) {
                    productBought = "5kg of dal";
                    return 400;
                }
                break;
            case "rice":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                 riceWeight = sc.nextInt();
                if (riceWeight == 1) {
                    productBought = "1kg of rice";
                    return 80;
                } else if (riceWeight == 2) {
                    productBought = "5kg of rice";
                    return 400;
                }
                break;
        }
        return 0;
    }
}
