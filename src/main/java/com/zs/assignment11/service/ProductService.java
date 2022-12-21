package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    List<Product> findAllProductsByCategory(int id) throws BadRequestException, ProductNotFoundException;

    Product saveProduct(Product product) throws BadRequestException;

    Optional<Product> findById(int id) throws BadRequestException, ProductNotFoundException;

    void delete(int id) throws BadRequestException;

    Product update(Product product) throws BadRequestException, ProductNotFoundException;

}
