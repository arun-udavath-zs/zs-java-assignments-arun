package com.zs.assignment10.dao;

import com.zs.assignment10.exception.DatabaseConnectionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    public Connection getDbConnection() throws DatabaseConnectionFailedException {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream;

            {
                try {
                    fileInputStream = new FileInputStream("/home/lenovo/Documents/zs-java-assignments/src/main/resources/application.properties");
                    properties.load(fileInputStream);
                    DB_URL = properties.getProperty("DB_URL");
                    USER = properties.getProperty("USER");
                    PASSWORD = properties.getProperty("PASSWORD");

                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionFailedException("Database connection error." + e.getMessage());
        }
    }
}
