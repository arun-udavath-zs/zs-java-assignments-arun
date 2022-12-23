package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDaoImpl;
import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.FileException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDaoImpl productDaoImpl;

    public ProductServiceImpl() {
        this.productDaoImpl = new ProductDaoImpl();
    }

    /**
     * This method is used to fetch all the product
     * @param tableName
     * @return
     * @throws InternalServerException
     * @throws BadRequestException
     */
    @Override
    public List<Product> findAll(String tableName) throws InternalServerException, BadRequestException {
        if (tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.findAll(tableName);
    }

    /**
     * This method is used to find the product by id
     * @param id
     * @param tableName
     * @return
     * @throws InternalServerException
     * @throws BadRequestException
     */
    @Override
    public Product findById(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.findById(id, tableName);

    }

    /**
     * This method is used to save the product
     * @param id
     * @param productName
     * @param price
     * @param tableName
     * @throws InternalServerException
     * @throws BadRequestException
     */
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

    /**
     * This method is used to delete the product by id
     * @param id
     * @param tableName
     * @throws InternalServerException
     * @throws BadRequestException
     */
    @Override
    public void deleteById(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        productDaoImpl.deleteById(id, tableName);
    }

    /**
     * This method is used to check table is exists or not
     * @param id
     * @param tableName
     * @return
     * @throws InternalServerException
     * @throws BadRequestException
     */
    @Override
    public boolean exist(int id, String tableName) throws InternalServerException, BadRequestException {
        if (id < 0 || tableName == null) {
            throw new BadRequestException("given input is not valid");
        }
        return productDaoImpl.exist(id, tableName);
    }

    /**
     * This method is used is used to create the table
     * @throws InternalServerException
     */
    @Override
    public void createTable() throws InternalServerException {
        productDaoImpl.createTable();
    }

    /**
     * This method is used to save the data into file
     * @param productList
     * @param filePath
     * @throws BadRequestException
     * @throws FileException
     */
    @Override
    public void saveToFile(List<Product> productList, String filePath) throws BadRequestException, FileException {
        if (filePath == null) {
            throw new BadRequestException("File path provided is null");
        }

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (Product product : productList) {
                fileWriter.write(product.toString() + "\n");
            }
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }

    }

}
