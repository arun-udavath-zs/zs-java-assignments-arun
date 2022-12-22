package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts() throws EntityNotFoundException;

    List<Product> findAllProductsByCategory(int id) throws BadRequestException, EntityNotFoundException;

    Product saveProduct(Product product) throws BadRequestException;

    Optional<Product> findById(int id) throws BadRequestException, EntityNotFoundException;

    Optional<Product> delete(int id) throws BadRequestException;

    Product update(int id, Product product) throws BadRequestException, EntityNotFoundException;

}
