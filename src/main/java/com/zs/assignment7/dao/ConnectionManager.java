package com.zs.assignment7.dao;

import com.zs.assignment7.exception.DatabaseConnectionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:2022/students";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    /**
     * This method is used to establish a connection with database
     * @return connection variable
     * @throws DatabaseConnectionFailedException
     */
    public Connection dbConnection() throws DatabaseConnectionFailedException {

        try {
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("database connected successfully");
            return connection;
        } catch (SQLException exception) {
            throw new DatabaseConnectionFailedException("Database connection error."+ exception.getMessage());
        }
    }
}
