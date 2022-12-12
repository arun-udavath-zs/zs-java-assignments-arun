package com.zs.assignment7.controller;

import com.zs.assignment7.service.JdbcService;

import java.io.IOException;
import java.sql.SQLException;

public class JdbcController {

    public void controller() throws SQLException, IOException {

        JdbcService service = new JdbcService();
        service.insertDataIntoStudentTable();
        service.saveDataIntoFile();
    }
}
