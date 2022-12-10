package com.zs.assignment10.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:2022/products";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public Connection dbConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
