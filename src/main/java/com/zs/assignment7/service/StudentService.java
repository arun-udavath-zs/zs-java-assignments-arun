package com.zs.assignment7.service;

import com.zs.assignment7.dao.ConnectionManager;
import com.zs.assignment7.exception.FileException;
import com.zs.assignment7.exception.InternalServerException;
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
    private static final int RECORD_SIZE = 1000000;
    private static final int BATCH_SIZE = 1000;
    private final List<Student> studentList = new ArrayList<>();
    private final ConnectionManager databaseConnection;
    private final RandomFunctionsGenerator randomFunctions;
    private final String filePath;
    String homeDir = System.getProperty("user.dir");

    public StudentService() {
        this.databaseConnection = new ConnectionManager();
        this.randomFunctions = new RandomFunctionsGenerator();
        filePath = homeDir + "/src/main/resources/dataOutput.txt";
    }

    /**
     * This method is used to create the student table
     *
     * @throws InternalServerException
     * @throws SQLException
     */
    public void createStudentTable() throws InternalServerException {
        String createQuery = "CREATE TABLE IF NOT EXISTS students (id serial PRIMARY KEY,first_name VARCHAR(50),last_name VARCHAR(50),mobile_num VARCHAR(20))";
        try (Connection connection = databaseConnection.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createQuery);
            logger.info("student table created successfully");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    /**
     * This method is used to insert the data in the student table
     *
     * @throws SQLException
     * @throws InternalServerException
     */
    public void insertDataIntoStudentTable() throws InternalServerException {
        String insertQuery = "INSERT INTO students (first_name,last_name,mobile_num) VALUES (?,?,?)";
        try (Connection connection = databaseConnection.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            logger.info("inserting data into student");

            for (int i = 1; i < RECORD_SIZE; i++) {
                preparedStatement.setString(1, randomFunctions.generateRandomName());
                preparedStatement.setString(2, randomFunctions.generateRandomName());
                preparedStatement.setString(3, randomFunctions.generateRandomMobile());
                preparedStatement.addBatch();
                if (i % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                }
            }
            logger.info("Inserted data into students table successfully..");
        } catch (SQLException e) {
            throw new InternalServerException("error." + e.getMessage());
        }
    }

    public void insertDataIntoDepartment() throws InternalServerException {
        String createQuery = "CREATE TABLE IF NOT EXISTS departments (id int,dept_name VARCHAR(50))";
        String insertQuery = "INSERT INTO departments(id, dept_name) VALUES ('0','CSE'), ('1','EE'), ('2','Mech')";
        try (Connection connection = databaseConnection.getDbConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createQuery);
            statement.executeUpdate(insertQuery);
            logger.info("inserted data into departments table Successfully...");
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    /**
     * This method is used to alter the student table
     *
     * @throws InternalServerException
     */
    public void alterStudentTable() throws InternalServerException {
        String sql = "ALTER TABLE students ADD dept_id int";
        try (Connection connection = databaseConnection.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new InternalServerException("error." + e.getMessage());
        }
        logger.info("altered the students successfully..");
    }

    /**
     * This method is used to update the table
     *
     * @throws InternalServerException
     */
    public void updateStudentTable() throws InternalServerException {
        String sql = "update students set dept_id = ? where id = ?";
        try (Connection connection = databaseConnection.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= RECORD_SIZE; i++) {
                int deptID = i % 3;
                preparedStatement.setInt(1, deptID);
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();

                if (i % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                }
            }
        } catch (SQLException e) {
            throw new InternalServerException("error." + e.getMessage());
        }
        logger.info("student table updated successfully..");
    }

    /**
     * This method is used to inner join the student and department table
     *
     * @return list of students data
     * @throws InternalServerException
     * @throws SQLException
     */

    public List<Student> getStudentWithDept() throws InternalServerException {
        String innerJoinQuery = "Select students.id, first_name, last_name, mobile_num, dept_name from students " +
                "INNER JOIN departments ON students.dept_id = departments.id ORDER BY students.id ASC ";
        try (Connection connection = databaseConnection.getDbConnection();
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
        } catch (SQLException e) {
            throw new InternalServerException("error." + e.getMessage());
        }
        logger.info("inner join performed successfully...");
        return studentList;
    }

    /**
     * This method is used to save th data into the file
     *
     * @param list
     * @throws IOException
     */
    public void saveToFile(List<Student> list) throws FileException {
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (Student student : list) {
                fileWriter.write(student.toString() + "\n");
            }
        } catch (IOException e) {
            throw new FileException("error." + e.getMessage());
        }
        logger.info("data saved into file successfully..");
    }

    /**
     * This method is used to compress the output file
     *
     * @throws IOException
     */
    public void compressFile() throws FileException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(homeDir + "/src/main/resources/compressed-file.bin");
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(fileOutputStream);

            int data;
            while ((data = fileInputStream.read()) != -1) {
                deflaterOutputStream.write(data);
            }

            fileInputStream.close();
            deflaterOutputStream.close();
        } catch (IOException e) {
            throw new FileException("error." + e.getMessage());
        }
        logger.info("file compressed successfully..");
    }
}

