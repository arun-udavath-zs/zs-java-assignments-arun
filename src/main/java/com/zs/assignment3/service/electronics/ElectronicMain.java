package com.zs.assignment3.service.electronics;

import static com.zs.assignment3.service.electronics.Phones.productBought;
public class ElectronicMain {
    private final Electronic electronic;
    public ElectronicMain(Electronic electronic) {
        this.electronic = electronic;
    }
    public void filter(){
        int price= electronic.filter();
        System.out.println("amount for your product "+ productBought+" is : "+ price);
        System.out.println("Thanks for shopping!!");
    }
}
