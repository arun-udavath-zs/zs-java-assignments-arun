package com.zs.assignment7.service;

import com.zs.assignment7.repository.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcService {

    private final static Logger logger = LoggerFactory.getLogger(JdbcService.class);

    /**
     * it will create the tables of students and departments
     *
     * @throws SQLException throws exception when database connection fails
     */
    public void jdbc() throws SQLException {

        DatabaseConnection dbConn = new DatabaseConnection();
        RandomFunctions randomFunctions = new RandomFunctions();
        Connection connection;
        PreparedStatement preparedStatement = null;
        Department department = new Department();
        Student student = new Student();

        try {
            connection = dbConn.dbConnection();
            department.InsertDataIntoDepartment();
            student.createStudentTable();
            String insertQuery = "INSERT INTO students (id,first_name,last_name,mobile,departments) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            for (int i = 1; i < 1000; i++) {
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setString(2, randomFunctions.generateName());
                preparedStatement.setString(3, randomFunctions.generateName());
                preparedStatement.setString(4, randomFunctions.generateMobile());
                preparedStatement.setString(5, randomFunctions.generateDepartment(Department.deptName));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("exception occured" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }
}
