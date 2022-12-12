package com.zs.assignment10.service;

import com.zs.assignment10.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateProductTable {
    public void tableCreation() {
        DatabaseConnection dbConnection = new DatabaseConnection();

        try (Connection connection = dbConnection.dbConnection();
             Statement statement = connection.createStatement()) {
            String createQuery = "CREATE TABLE IF NOT EXISTS products (id int, product_name VARCHAR(20), price int)";
            statement.executeUpdate(createQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
