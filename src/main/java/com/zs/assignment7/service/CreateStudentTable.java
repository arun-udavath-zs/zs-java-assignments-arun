package com.zs.assignment7.service;

import com.zs.assignment7.repository.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStudentTable {

    private final static Logger logger = LoggerFactory.getLogger(CreateStudentTable.class);
    String createQuery = "CREATE TABLE IF NOT EXISTS students (id VARCHAR(10),first_name VARCHAR(50),last_name VARCHAR(50),mobile VARCHAR(50),departments VARCHAR(50))";

    public void createStudentTable() {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.dbConnection();
             Statement statement = connection.createStatement()) {
             statement.executeUpdate(createQuery);
             logger.info("student table created successfully");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
