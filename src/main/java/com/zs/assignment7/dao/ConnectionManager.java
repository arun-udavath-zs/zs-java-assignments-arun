package com.zs.assignment7.dao;

import com.zs.assignment7.exception.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:2022/students";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    /**
     * This method is used to establish a connection with database
     *
     * @return connection variable
     * @throws InternalServerException
     */
    public Connection getDbConnection() throws InternalServerException {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            LOGGER.info("database connected successfully");
            return connection;
        } catch (SQLException exception) {
            throw new InternalServerException("Database connection error." + exception.getMessage());
        }
    }
}
