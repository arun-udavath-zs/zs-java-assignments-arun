package com.zs.assignment7;

import com.zs.assignment7.controller.JdbcController;

import java.io.IOException;
import java.sql.SQLException;

public class JdbcMain {
    public static void main(String[] args) throws SQLException, IOException {
        JdbcController jdbcController = new JdbcController();
        jdbcController.controller();
    }
}
