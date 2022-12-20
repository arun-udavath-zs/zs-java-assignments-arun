package com.zs.assignment11.controller;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import com.zs.assignment11.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductRepository productRepository;

    ProductController(ProductService productService,
                      ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        LOGGER.info("to fetch the product with id " + id);
        try {
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        } catch (BadRequestException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<?> getProductByCategory(@PathVariable int categoryId) {
        LOGGER.info("to fetch the product with category " + categoryId);
        try {
            return new ResponseEntity<>(productService.findAllProductsByCategory(categoryId), HttpStatus.OK);
        } catch (BadRequestException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        LOGGER.info("entered in addProduct");
        try {
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        LOGGER.info("to delete the product with id " + id);
        try {
            productService.delete(id);
        } catch (BadRequestException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        LOGGER.info("entered in update product");
        try {
          return new ResponseEntity<>(productService.update(product),HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
