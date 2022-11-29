package com.zs.assignment3.service.groceries;

import static com.zs.assignment3.service.electronics.Phones.productBought;

public class Groceries {
    GroceriesItem grocery;

    public Groceries(GroceriesItem grocery) {
        this.grocery = grocery;
    }

    public void filter() {
        int price = grocery.filter();
        System.out.println("amount for your grocery " + productBought + " is : " + price);
        System.out.println("Thanks for shopping!!");
    }

}
