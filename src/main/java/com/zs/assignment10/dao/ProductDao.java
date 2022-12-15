package com.zs.assignment10.dao;

import com.zs.assignment10.exception.DatabaseConnectionFailedException;
import com.zs.assignment10.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findAll(String tableName) throws SQLException, DatabaseConnectionFailedException;

    Product findById(int id, String tableName) throws SQLException, DatabaseConnectionFailedException;

    void insert(int id, String productName, int price, String tableName) throws SQLException, DatabaseConnectionFailedException;

    void update(int id, String productName, int price, String tableName) throws SQLException, DatabaseConnectionFailedException;

    void deleteById(int id, String tableName) throws SQLException, DatabaseConnectionFailedException;

    boolean exist(int id, String tableName) throws DatabaseConnectionFailedException;

    void tableCreation() throws DatabaseConnectionFailedException;
}
