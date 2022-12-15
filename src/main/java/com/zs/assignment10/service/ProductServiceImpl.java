package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDaoImpl;
import com.zs.assignment10.exception.DatabaseConnectionFailedException;
import com.zs.assignment10.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDaoImpl productRepoImpl;

    public ProductServiceImpl() {
        this.productRepoImpl = new ProductDaoImpl();
    }

    @Override
    public List<Product> findAll(String tableName) throws DatabaseConnectionFailedException {
        return productRepoImpl.findAll(tableName);
    }

    @Override
    public Product findById(int id, String tableName) throws DatabaseConnectionFailedException {
        return productRepoImpl.findById(id, tableName);

    }

    @Override
    public void insert(int id, String productName, int price, String tableName) throws DatabaseConnectionFailedException {
        productRepoImpl.insert(id, productName, price, tableName);
    }

    @Override
    public void update(int id, String productName, int price, String tableName) throws DatabaseConnectionFailedException {
        productRepoImpl.update(id, productName, price, tableName);
    }

    @Override
    public void deleteById(int id, String tableName) throws DatabaseConnectionFailedException {
        productRepoImpl.deleteById(id, tableName);
    }

    @Override
    public boolean exist(int id, String tableName) throws DatabaseConnectionFailedException {
        return productRepoImpl.exist(id, tableName);
    }

    @Override
    public void tableCreation() throws DatabaseConnectionFailedException {
        productRepoImpl.tableCreation();
    }
}
