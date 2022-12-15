package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(int id);

    List<String> getAllCategories();

    List<Product> getProductByCategory(String category);

    Product saveProduct(Product product);

    void deleteProduct(int id) throws BadRequestException;

    void updateProduct(Product product) throws BadRequestException;

}
