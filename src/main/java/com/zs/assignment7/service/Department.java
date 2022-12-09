package com.zs.assignment7.service;

import com.zs.assignment7.repository.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class Department {
    private final static Logger logger = LoggerFactory.getLogger(Department.class);
    String createQuery = "CREATE TABLE IF NOT EXISTS departments (id VARCHAR(10),dept_name VARCHAR(50))";
    public static ArrayList<String> deptName = new ArrayList<>();

    /**
     * it used to insert the data in department table
     *
     * @throws SQLException throws exception when database connection fails
     */

    public void InsertDataIntoDepartment() throws SQLException {


        DatabaseConnection dbConn = new DatabaseConnection();

        try (Connection connection = dbConn.dbConnection();
             Statement statement = connection.createStatement();) {

            statement.executeUpdate(createQuery);
            logger.info("Created departments table in given database...");

            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('0','CSE')");
            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('1','EE')");
            statement.executeUpdate("INSERT INTO departments(id, dept_name) VALUES ('2','Mech')");

            PreparedStatement preparedstatement = connection.prepareStatement("select dept_name from departments");
            ResultSet rs = preparedstatement.executeQuery();

            while (rs.next()) {
                String dept = rs.getString(1);
                deptName.add(dept);
            }

        } catch (SQLException e) {
            logger.error("exception occured " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
