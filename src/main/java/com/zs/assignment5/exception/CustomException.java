package com.zs.assignment5.exception;

public class CustomException extends Exception {

    public CustomException(String message) {
        super("custom defined exception : " + message);
    }
}
