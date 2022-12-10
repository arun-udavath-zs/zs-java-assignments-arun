package com.zs.assignment11.service;

import com.zs.assignment11.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProduct();
    void saveProduct(Product product);
    void deleteProduct(int id);
    void updateProduct(Product product);
}
