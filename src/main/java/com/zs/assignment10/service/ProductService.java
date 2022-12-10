package com.zs.assignment10.service;

import com.zs.assignment10.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> findAll(String tableName) throws SQLException;

    Product findById(int id, String tableName) throws SQLException;

    void insert(int id, String productName, int price, String tableName) throws SQLException;

    void update(int id, String productName, int price, String tableName) throws SQLException;

    void deleteById(int id, String tableName) throws SQLException;

    boolean exist(int id, String tableName);
}
