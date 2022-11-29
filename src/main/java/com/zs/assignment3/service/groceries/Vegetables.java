package com.zs.assignment3.service.groceries;

import java.util.Scanner;
import static com.zs.assignment3.service.electronics.Phones.productBought;
public class Vegetables implements GroceriesItem{
    String item;
    public Vegetables(String item){
        this.item=item;
    }
    // to filter the vegetable
    public int filter(){
        Scanner sc= new Scanner(System.in);
        switch (item){
            case "tomato":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                int weight = sc.nextInt();
                if(weight == 1) {
                    productBought = "1kg of tomato";
                    return 80;
                }
                else if(weight == 2) {
                    productBought = "5kg of tomato";
                    return 400;
                }
                break;
            case "potato":
                System.out.println("press 1 for 1 kg and press 2 for 5kg");
                int item = sc.nextInt();
                if(item == 1) {
                    productBought = "1kg of potato";
                    return 80;
                }
                else if(item == 2) {
                    productBought = "5kg of potato";
                    return 400;
                }
                break;
        }
        return 0;
    }
}
