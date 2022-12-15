package com.zs.assignment10.controller;

import com.zs.assignment10.exception.DatabaseConnectionFailedException;
import com.zs.assignment10.exception.FilePathNotValidException;
import com.zs.assignment10.model.Product;
import com.zs.assignment10.service.ProductService;
import com.zs.assignment10.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
        try {
            productService.tableCreation();
        } catch (DatabaseConnectionFailedException e) {
            logger.error("Something went wrong: " + e.getMessage());
        }
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int id, price, choice;
        String productName, tableName, filePath;
        List<Product> productList;

        do {
            logger.info("Enter the choice of operation\n1. to get all the products\n2. to find the product" +
                    "\n3. to insert the product\n4. to update the product\n5. to delete the product\n" +
                    "6. to check a product exists\n7. to stop ");
            choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        logger.info("Enter the table name and filePath");
                        tableName = sc.next();
                        filePath = sc.next();
                        productList = productService.findAll(tableName);
                        saveToFile(productList, filePath);
                        logger.info("successfully fetched the data");
                        break;
                    case 2:
                        logger.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        Product product = productService.findById(id, tableName);
                        logger.info(product.toString());
                        break;
                    case 3:
                        logger.info("Enter id, productName,price and tableName");
                        id = sc.nextInt();
                        productName = sc.next();
                        price = sc.nextInt();
                        tableName = sc.next();
                        productService.insert(id, productName, price, tableName);
                        break;
                    case 4:
                        logger.info("Enter the id, productName, price, tableName");
                        id = sc.nextInt();
                        productName = sc.next();
                        price = sc.nextInt();
                        tableName = sc.next();
                        productService.update(id, productName, price, tableName);
                        break;
                    case 5:
                        logger.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        productService.deleteById(id, tableName);
                        break;
                    case 6:
                        logger.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        boolean exist = productService.exist(id, tableName);
                        if (exist)
                            logger.info("given id exists in the database");
                        else
                            logger.info("given id doesn't exist in the database");
                        break;
                    case 7:
                        break;
                    default:
                        logger.info("Please enter the correct option");
                        start();

                }
            } catch (DatabaseConnectionFailedException e) {
                logger.error("Something went wrong: " + e.getMessage());
            } catch (FilePathNotValidException e) {
                logger.error("Something went wrong: " + e.getMessage());
            } catch (SQLException e) {
                logger.error("Something went wrong: " + e.getMessage());
            } catch (IOException e) {
                logger.error("Something went wrong: " + e.getMessage());
            }
        } while (choice != 7);
    }

    public void saveToFile(List<Product> productList, String filePath) throws IOException, FilePathNotValidException {
        if (filePath == null) {
            throw new FilePathNotValidException("File path provided is null");
        }

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (Product product : productList) {
                fileWriter.write(product.toString() + "\n");
            }
        }

    }
}
