package com.zs.assignment9;

import com.zs.assignment9.controller.Controller;

public class UnitTesting {
    public static void main(String[] args) {

        Controller c = new Controller();
        try {
            c.studentController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
