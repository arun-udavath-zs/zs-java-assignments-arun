package com.zs.assignment10.dao;

import com.zs.assignment10.exception.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public Connection getDbConnection(String dbUrl, String userName, String password) throws InternalServerException {

        try {
            return DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            throw new InternalServerException("error." + e.getMessage());
        }
    }
}
