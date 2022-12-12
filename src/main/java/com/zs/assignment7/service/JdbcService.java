package com.zs.assignment7.service;

import com.zs.assignment7.repository.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcService {

    private final static Logger logger = LoggerFactory.getLogger(JdbcService.class);

    private static final String path = "/home/lenovo/Documents/zs-java-assignments/src/main/resources/dataOutput.txt";
    String insertQuery = "INSERT INTO students (id,first_name,last_name,mobile,departments) VALUES (?,?,?,?,?)";

    /**
     * it will create the tables of students and departments
     *
     */
    public void insertDataStudentTable() {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        RandomFunctionsGenerator randomFunctions = new RandomFunctionsGenerator();
        AddDataIntoDepartmentTable department = new AddDataIntoDepartmentTable();
        CreateStudentTable student = new CreateStudentTable();

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
             department.InsertDataIntoDepartment();
             student.createStudentTable();

            for (int i = 1; i < 1000; i++) {
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setString(2, randomFunctions.generateRandomName());
                preparedStatement.setString(3, randomFunctions.generateRandomName());
                preparedStatement.setString(4, randomFunctions.generateRandomMobile());
                preparedStatement.setString(5, randomFunctions.generateRandomDepartment(AddDataIntoDepartmentTable.deptName));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            logger.error("exception occured" + exception.getMessage());
            exception.printStackTrace();
        }
    }
    public void saveDataIntoFile() throws SQLException, IOException {
        FetchDataFromDB fetch = new FetchDataFromDB();
        fetch.addDataIntoFile(path);
    }
}
