package com.zs.assignment11.service;

import com.zs.assignment11.exception.IdNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * to fetch all products in the database
     * @return list of all products
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * to fetch the product by id
     * @param id to fetch the product with unique id
     * @return product with the given id
     */
    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    /**
     * to fetch the product with given category
     * @param category to fetch the products with given category
     * @return list of products with given category
     */
    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.getProductByCategory(category);
    }

    /**
     * to add the product in the database
     * @param product a product to add in database
     * @return a saved product
     */
    @Override
    public Product saveProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            log.error("Product with " + product.getId() + " already exists");
            throw new IllegalStateException("Product with " + product.getId() + " already exists");
        }
        return productRepository.save(product);
    }

    /**
     * to delete the product with given id
     * @param id delete the product with this id
     * @throws IdNotFoundException throws exception if id does not exist
     */
    @Override
    public void deleteProduct(int id) throws IdNotFoundException {
        boolean exits = productRepository.existsById(id);
        if (!exits) {
            log.error("product with id " + id + " does not exists");
            throw new IdNotFoundException("product with id " + id + " does not exists");
        }
        productRepository.deleteById(id);
    }

    /**
     * to update the product in the database
     * @param product product to update
     * @throws IdNotFoundException throws exception if id does not exist
     */
    @Override
    public void updateProduct(Product product) throws IdNotFoundException {
        boolean exists = productRepository.existsById(product.getId());
        if (!exists) {
            log.error("product with id " + product.getId() + " does not exists");
            throw new IdNotFoundException("product with id " + product.getId() + " does not exists");
        }
        productRepository.save(product);
    }

}
