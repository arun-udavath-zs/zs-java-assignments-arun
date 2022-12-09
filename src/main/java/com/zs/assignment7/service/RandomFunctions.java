package com.zs.assignment7.service;

import java.util.List;
import java.util.Random;

public class RandomFunctions {
    /**
     * used to generate a random name
     *
     * @return a random name
     */
    public String generateName() {

        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * 26);
            buffer.append(str.charAt(rand));
        }
        return buffer.toString();
    }

    /**
     * used to generate a random number
     *
     * @return a random number
     */
    public String generateMobile() {

        Random random = new Random();
        String num1, num2, num3,res;
        num1 = String.valueOf(random.nextInt(9000) + 100);
        num2 = String.valueOf(random.nextInt(643) + 100);
        num3 = String.valueOf(random.nextInt(9000) + 1000);
        res = num1 + num2 + num3;
        return res;
    }

    /**
     * used to generate a random department
     *
     * @param array list of department names
     * @return a random departments
     */
    public String generateDepartment(List<String> array) {

        int rand = new Random().nextInt(array.size());
        return array.get(rand);
    }
}
