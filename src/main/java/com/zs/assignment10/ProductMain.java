package com.zs.assignment10;

import com.zs.assignment10.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMain {

    private static final Logger logger = LoggerFactory.getLogger(ProductMain.class);

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        try {
            productController.serviceController();
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
