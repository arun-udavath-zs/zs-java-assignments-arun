package com.zs.assignment7.controller;

import com.zs.assignment7.service.FetchInformation;
import com.zs.assignment7.service.JdbcService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcController {

    private static final String path = "/home/lenovo/Documents/zs-java-assignments/src/main/resources/dataOutput.txt";

    public void controller() throws SQLException, IOException {
        JdbcService service = new JdbcService();
        service.jdbc();
        FetchInformation fetch = new FetchInformation();
        ResultSet resultSet = fetch.readDataFromDatabase();
        fetch.addDataIntoFile(resultSet, path);
    }
}
