package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsByCategory(int categoryId) throws BadRequestException, ProductNotFoundException {
        if (categoryId <0) {
            throw new BadRequestException("Category id cannot be negative");
        }
        List<Product> productList= productRepository.findAllProductsByCategory(categoryId);
        if (productList!=null){
            return productList;
        }
        throw new ProductNotFoundException("product with given id doesn't exists");
    }

    @Override
    public Product saveProduct(Product product) throws BadRequestException {
        if (product.getId() < 0 || product.getProductName()== null || product.getPrice()<0 || product.getCategoryId()<0)
            throw new BadRequestException("given input is not valid");
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(int id) throws BadRequestException, ProductNotFoundException {
        if (id <0) {
            throw new BadRequestException("Id cannot be negative");
        }
        Optional<Product> product= productRepository.findById(id);
        if (product!=null) {
             return product;
        }
        throw new ProductNotFoundException("product with given id doesn't exists");
    }

    @Override
    public void delete(int id) throws BadRequestException {
        if (id < 0){
            throw new BadRequestException("Id cannot be negative");
        }
       productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) throws BadRequestException {
        if (product.getId() < 0 || product.getProductName()== null || product.getPrice()<0 || product.getCategoryId()<0)
            throw new BadRequestException("given input is not valid");
        return productRepository.save(product);
    }
}
