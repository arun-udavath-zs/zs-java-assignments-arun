package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is used to fetch all the products
     * @return
     */
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * This method is used to find the products with given category
     * @param categoryId
     * @return
     * @throws BadRequestException
     * @throws ProductNotFoundException
     */
    @Override
    public List<Product> findAllProductsByCategory(int categoryId) throws BadRequestException, ProductNotFoundException {
        if (categoryId < 0) {
            throw new BadRequestException("Category id cannot be negative");
        }
        List<Product> productList = productRepository.findAllProductsByCategory(categoryId);
        if (productList != null) {
            return productList;
        }
        throw new ProductNotFoundException("product with given id doesn't exists");
    }

    /**
     * This method is used to save the product
     * @param product
     * @return
     * @throws BadRequestException
     */
    @Override
    public Product saveProduct(Product product) throws BadRequestException {
        if (product.getId() < 0 || product.getProductName() == null || product.getPrice() < 0 || product.getCategoryId() < 0)
            throw new BadRequestException("given input is not valid");
        if (productRepository.existsById(product.getId()))
            throw new BadRequestException("product with given id already exists");
        return productRepository.save(product);
    }

    /**
     * This method is used to find the product with id
     * @param id
     * @return
     * @throws BadRequestException
     * @throws ProductNotFoundException
     */
    @Override
    public Optional<Product> findById(int id) throws BadRequestException, ProductNotFoundException {
        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }
        Optional<Product> product = productRepository.findById(id);
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException("product with given id doesn't exists");
    }

    /**
     * This method is used to delete the product
     * @param id
     * @throws BadRequestException
     */
    @Override
    public void delete(int id) throws BadRequestException {
        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }
        productRepository.deleteById(id);
    }

    /**
     * This method is used to update the product
     * @param product
     * @return
     * @throws BadRequestException
     * @throws ProductNotFoundException
     */
    @Override
    public Product update(Product product) throws BadRequestException, ProductNotFoundException {
        if (product.getId() < 0 || product.getProductName() == null || product.getPrice() < 0 || product.getCategoryId() < 0)
            throw new BadRequestException("given input is not valid");
        if (!productRepository.existsById(product.getId()))
            throw new ProductNotFoundException("product doesn't exists with given id");
        return productRepository.save(product);
    }
}
