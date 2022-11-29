package com.zs.assignment3.service.electronics;

import java.util.Scanner;
import static com.zs.assignment3.service.electronics.Phones.productBought;
public class Televisions implements Electronic{
    String brand;
    public Televisions(String brand){
        this.brand= brand;
    }
    // to filter the television
    public int filter(){
        Scanner sc= new Scanner(System.in);
        switch (brand){
            case "lg":
                System.out.println("press 1 for 64inches and press 2 for 124inches television");
                int television = sc.nextInt();
                if(television == 1) {
                    productBought = "lg of 64inches";
                    return 120000;
                }
                else if(television == 2) {
                    productBought = "lg of 124inches";
                    return 80000;
                }
                break;

            case "samsung":
                System.out.println("press 1 for 64inches and press 2 for 124inches television");
                int item = sc.nextInt();
                if(item == 1) {
                    productBought = "samsung of 64inches";
                    return 120000;
                }
                else if(item == 2) {
                    productBought = "samsung of 124inches";
                    return 80000;
                }
                break;
        }
        return 0;
    }
}
