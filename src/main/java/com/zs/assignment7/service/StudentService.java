package com.zs.assignment7.service;

import com.zs.assignment7.dao.ConnectionManager;
import com.zs.assignment7.exception.DatabaseConnectionFailedException;
import com.zs.assignment7.model.Student;
import com.zs.assignment7.util.RandomFunctionsGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private static final String createQuery = "CREATE TABLE IF NOT EXISTS students (id int,first_name VARCHAR(50),last_name VARCHAR(50),mobile_num VARCHAR(20),dept_id int)";
    private static final String insertQuery = "INSERT INTO students (id,first_name,last_name,mobile_num,dept_id) VALUES (?,?,?,?,?)";
    private static final String filePath = "/home/lenovo/Documents/zs-java-assignments/src/main/resources/dataOutput.txt";
    private static final String innerJoinQuery = "Select students.id, first_name, last_name, mobile_num, dept_name from students " +
            "INNER JOIN departments ON students.dept_id = departments.id ORDER BY students.id ASC ";
    private static final int RECORD_SIZE = 1000000;
    private static final int BATCH_SIZE = 1000;
    private final List<Student> studentList = new ArrayList<>();
    private final ConnectionManager databaseConnection;
    private final RandomFunctionsGenerator randomFunctions;

    public StudentService() {
        this.databaseConnection = new ConnectionManager();
        this.randomFunctions = new RandomFunctionsGenerator();
    }


    /**
     * This method is used to create the student table
     * @throws DatabaseConnectionFailedException
     * @throws SQLException
     */
    public void createStudentTable() throws DatabaseConnectionFailedException, SQLException {

        try (Connection connection = databaseConnection.dbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createQuery);
            logger.info("student table created successfully");
        }
    }

    /**
     * This method is used to insert the data in the student table
     *
     * @throws SQLException
     * @throws DatabaseConnectionFailedException
     */
    public void insertDataIntoStudentTable() throws SQLException, DatabaseConnectionFailedException {

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            logger.info("inserting data into student");
            for (int i = 1; i < RECORD_SIZE; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, randomFunctions.generateRandomName());
                preparedStatement.setString(3, randomFunctions.generateRandomName());
                preparedStatement.setString(4, randomFunctions.generateRandomMobile());
                preparedStatement.setInt(5, i % 3);
                preparedStatement.addBatch();
                if (i % BATCH_SIZE == 0) {
                    preparedStatement.executeUpdate();
                }
            }
            logger.info("Inserted data into students table successfully..");
        }
    }

    /**
     * This method is used to inner join the student and department table
     * @return list of students data
     * @throws DatabaseConnectionFailedException
     * @throws SQLException
     */

    public List<Student> innerJoinStudentAndDept() throws DatabaseConnectionFailedException, SQLException {

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(innerJoinQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setMobileNo(resultSet.getLong("mobile_num"));
                student.setDepartment(resultSet.getString("dept_name"));
                studentList.add(student);
            }
            resultSet.close();
        }
        logger.info("inner join performed successfully...");
        return studentList;
    }

    /**
     * This method is used to save th data into the file
     * @param list
     * @throws IOException
     */
    public void saveToFile(List<Student> list) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (Student student : list) {
                fileWriter.write(student.toString() + "\n");
            }
        }
        logger.info("data saved into file successfully..");
    }

    /**
     * This method is used to compress the output file
     * @throws IOException
     */
    public void compressFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream("/home/lenovo/Documents/zs-java-assignments/src/main/resources/compressed-file.txt");
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(fileOutputStream);

        int data;
        while ((data = fileInputStream.read()) != -1) {
            deflaterOutputStream.write(data);
        }

        fileInputStream.close();
        deflaterOutputStream.close();
        logger.info("file compressed successfully..");
    }
}
