package com.zs.assignment7.util;

import java.util.Random;

public class RandomFunctionsGenerator {
    /**
     *This method is used to generate a random name
     *
     * @return a random name
     */
    public String generateRandomName() {

        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 26);
            buffer.append(str.charAt(random));
        }
        return buffer.toString();
    }

    /**
     * This method is used to generate a random mobile number
     *
     * @return a random number
     */
    public String generateRandomMobile() {

        Random random = new Random();
        String num1, num2, num3,res;
        num1 = String.valueOf(random.nextInt(9000) + 100);
        num2 = String.valueOf(random.nextInt(643) + 100);
        num3 = String.valueOf(random.nextInt(9000) + 1000);
        res = num1 + num2 + num3;
        return res;
    }


}
