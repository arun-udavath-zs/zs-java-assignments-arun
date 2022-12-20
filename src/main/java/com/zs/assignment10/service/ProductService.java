package com.zs.assignment10.service;

import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.FileException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(String tableName) throws InternalServerException, BadRequestException;

    Product findById(int id, String tableName) throws InternalServerException, BadRequestException;

    void insert(int id, String productName, int price, String tableName) throws InternalServerException, BadRequestException;

    void update(int id, String productName, int price, String tableName) throws InternalServerException, BadRequestException;

    void deleteById(int id, String tableName) throws InternalServerException, BadRequestException;

    boolean exist(int id, String tableName) throws InternalServerException, BadRequestException;

    void createTable() throws InternalServerException;

    void saveToFile(List<Product> productList, String filePath) throws FileException, BadRequestException;
}
