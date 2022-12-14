package com.zs.assignment7.service;

import com.zs.assignment7.dao.ConnectionManager;
import com.zs.assignment7.exception.DatabaseConnectionFailedException;
import com.zs.assignment7.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    private static final String createQuery = "CREATE TABLE IF NOT EXISTS departments (id int,dept_name VARCHAR(50))";
    private final ConnectionManager databaseConnection;

    public DepartmentService() {
        this.databaseConnection = new ConnectionManager();
    }

    /**
     * This method is used to insert the data in department table
     * @throws DatabaseConnectionFailedException
     * @throws InternalServerErrorException
     */

    public void insertDataIntoDepartment() throws DatabaseConnectionFailedException, InternalServerErrorException {

        try (Connection connection = databaseConnection.dbConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createQuery);
            logger.info("Created departments table in given database...");
            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('0','CSE')");
            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('1','EE')");
            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('2','Mech')");

        } catch (SQLException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
