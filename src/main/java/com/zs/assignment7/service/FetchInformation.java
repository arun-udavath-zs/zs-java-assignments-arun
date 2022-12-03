package com.zs.assignment7.service;

import com.zs.assignment7.repository.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FetchInformation {

    private static final Logger logger = LoggerFactory.getLogger(FetchInformation.class);

    /**
     * used to add the data into the file
     *
     * @param resultSet it used the stored the fetched data from database
     * @param filePath  it is the path of the file used to store the data which is fetched
     * @throws SQLException throws exception when database connection fails
     * @throws IOException  throws exception when file doesn't exit
     */
    public void addDataIntoFile(ResultSet resultSet, String filePath) throws SQLException, IOException {

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String mobile = resultSet.getString(4);
                String department = resultSet.getString(5);

                String record = String.format("ID: %S, firstName: %s, lastName: %s, mobile: %s, department: %s", id, firstName, lastName, mobile, department);
                fileWriter.write(record);
                fileWriter.write("\n");
            }
        }

    }

    /**
     * used to store the data from the database
     *
     * @return ResultSet which stores the data which is fetched from database
     * * @throws SQLException throws exception when database connection fails
     */
    public ResultSet readDataFromDatabase() {
        DatabaseConnection dbConn = new DatabaseConnection();
        Connection connection;
        PreparedStatement statement;

        try {
            connection = dbConn.dbConnection();
            String sql = "select * from students limit 50;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            logger.info("Data fetched successfully from table");
            return resultSet;
        } catch (SQLException e) {
            logger.error("exception occured :" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
