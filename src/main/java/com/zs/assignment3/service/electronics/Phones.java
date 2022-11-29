package com.zs.assignment3.service.electronics;

import java.util.Scanner;

public class Phones implements Electronic {
    public static String productBought;
    String brand;

    public Phones(String brand) {
        this.brand = brand;
    }

    // to filter the phones
    public int filter() {
        Scanner sc = new Scanner(System.in);
        switch (brand) {
            case "Apple":
                System.out.println("press 1 for Iphone 14pro and press 2 for iphoneX");
                int phone = sc.nextInt();
                if (phone == 1) {
                    productBought = "Iphone14Pro";
                    return 120000;
                } else if (phone == 2) {
                    productBought = "IphoneX";
                    return 80000;
                }
                break;
            case "OnePlus":
                System.out.println("press 1 for iphone onePlus14pro and press 2 for onePlus10T");
                int phone_input = sc.nextInt();
                if (phone_input == 1) {
                    productBought = "onePlus14Pro";
                    return 70000;
                } else if (phone_input == 2) {
                    productBought = "onePlus10T";
                    return 50000;
                }
                break;
        }
        return 0;
    }
}
