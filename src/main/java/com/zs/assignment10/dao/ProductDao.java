package com.zs.assignment10.dao;

import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll(String tableName) throws InternalServerException;

    Product findById(int id, String tableName) throws InternalServerException;

    void insert(int id, String productName, int price, String tableName) throws InternalServerException;

    void update(int id, String productName, int price, String tableName) throws InternalServerException;

    void deleteById(int id, String tableName) throws InternalServerException;

    boolean exist(int id, String tableName) throws InternalServerException;

    void createTable() throws InternalServerException;
}
