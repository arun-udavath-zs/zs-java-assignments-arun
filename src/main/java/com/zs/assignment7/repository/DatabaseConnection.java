package com.zs.assignment7.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:2022/dockerinfo";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    /**
     * used to establish a connection with database
     *
     * @return a connection of database
     * @throws SQLException throws exception when database connection fails
     */
    public Connection dbConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("database connected successfully");
        } catch (SQLException exception) {
            logger.error("exception occured :" + exception.getMessage());
            exception.printStackTrace();
        }
        return connection;
    }
}
