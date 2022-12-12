package com.zs.assignment11.service;

import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(int id);

    List<Product> getProductByCategory(String category);

    Product saveProduct(Product product);

    void deleteProduct(int id) throws ProductNotFoundException;

    void updateProduct(Product product) throws ProductNotFoundException;

}
