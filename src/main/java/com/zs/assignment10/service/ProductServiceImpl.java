package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDaoImpl;
import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDaoImpl productDaoImpl;

    public ProductServiceImpl() {
        this.productDaoImpl = new ProductDaoImpl();
    }

    @Override
    public List<Product> findAll(String tableName) throws InternalServerException, BadRequestException {
        if (tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.findAll(tableName);
    }

    @Override
    public Product findById(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.findById(id, tableName);

    }

    @Override
    public void insert(int id, String productName, int price, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || productName == null || price < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        productDaoImpl.insert(id, productName, price, tableName);
    }

    @Override
    public void update(int id, String productName, int price, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || productName == null || price < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        productDaoImpl.update(id, productName, price, tableName);
    }

    @Override
    public void deleteById(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        productDaoImpl.deleteById(id, tableName);
    }

    @Override
    public boolean exist(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.exist(id, tableName);
    }

    @Override
    public void createTable() throws InternalServerException {
        productDaoImpl.createTable();
    }

}
