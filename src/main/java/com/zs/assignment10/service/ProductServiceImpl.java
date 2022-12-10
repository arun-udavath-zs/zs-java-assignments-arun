package com.zs.assignment10.service;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.repository.ProductRepoImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepoImpl productRepoImpl;

    public ProductServiceImpl() {
        this.productRepoImpl = new ProductRepoImpl();
    }

    @Override
    public List<Product> findAll(String tableName) {
        return productRepoImpl.findAll(tableName);
    }

    @Override
    public Product findById(int id, String tableName) {
        return productRepoImpl.findById(id, tableName);

    }

    @Override
    public void insert(int id, String productName, int price, String tableName) {
        productRepoImpl.insert(id, productName, price, tableName);
    }

    @Override
    public void update(int id, String productName, int price, String tableName) {
        productRepoImpl.update(id, productName, price, tableName);
    }

    @Override
    public void deleteById(int id, String tableName) {
        productRepoImpl.deleteById(id, tableName);
    }

    @Override
    public boolean exist(int id, String tableName) {
        return productRepoImpl.exist(id, tableName);
    }
}
