package com.zs.assignment5;


import java.text.ParseException;

public class ExceptionMain {
    public static void main(String[] args) {
        ParseController parse = new ParseController();
        try {
            parse.parser();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
