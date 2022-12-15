package com.zs.assignment10.exception;

public class DatabaseConnectionFailedException extends Exception {
    public DatabaseConnectionFailedException(String s) {
        super(s);
    }
}
