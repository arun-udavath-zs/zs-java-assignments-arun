package com.zs.assignment10.controller;

import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.FileException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;
import com.zs.assignment10.service.ProductService;
import com.zs.assignment10.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int id, price, choice;
        String productName, tableName, filePath;
        List<Product> productList;

        try {
            productService.createTable();

            do {
                LOGGER.info("Enter the choice of operation\n1. to get all the products\n2. to find the product" +
                        "\n3. to insert the product\n4. to update the product\n5. to delete the product\n" +
                        "6. to check a product exists\n7. to stop ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        LOGGER.info("Enter the table name and filePath");
                        tableName = sc.next();
                        filePath = sc.next();
                        productList = productService.findAll(tableName);
                        productService.saveToFile(productList, filePath);
                        LOGGER.info("successfully fetched the data");
                        break;
                    case 2:
                        LOGGER.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        Product product = productService.findById(id, tableName);
                        LOGGER.info("product : " + product.toString());
                        break;
                    case 3:
                        LOGGER.info("Enter id, productName,price and tableName");
                        id = sc.nextInt();
                        productName = sc.next();
                        price = sc.nextInt();
                        tableName = sc.next();
                        productService.insert(id, productName, price, tableName);
                        LOGGER.info("product inserted successfully");
                        break;
                    case 4:
                        LOGGER.info("Enter the id, productName, price, tableName");
                        id = sc.nextInt();
                        productName = sc.next();
                        price = sc.nextInt();
                        tableName = sc.next();
                        productService.update(id, productName, price, tableName);
                        LOGGER.info("product saved in the database successfully");
                        break;
                    case 5:
                        LOGGER.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        productService.deleteById(id, tableName);
                        LOGGER.info("product deleted successfully");
                        break;
                    case 6:
                        LOGGER.info("Enter the id and tableName");
                        id = sc.nextInt();
                        tableName = sc.next();
                        boolean exist = productService.exist(id, tableName);
                        if (exist)
                            LOGGER.info("given id exists in the database");
                        else
                            LOGGER.info("given id doesn't exist in the database");
                        break;
                    case 7:
                        break;
                    default:
                        LOGGER.info("Please enter the correct option");
                        start();

                }
            } while (choice != 7);
        } catch (InternalServerException e) {
            LOGGER.error("Something went wrong: " + e.getMessage());
        } catch (FileException e) {
            LOGGER.error("Something went wrong: " + e.getMessage());
        } catch (BadRequestException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
