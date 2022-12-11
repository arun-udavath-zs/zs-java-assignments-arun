package com.zs.assignment11.controller;

import com.zs.assignment11.exception.IdNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        log.info("to fetch the product with id "+id);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getProductByCategory")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category) {
        log.info("to fetch the product with category "+category);
        return new ResponseEntity<>(productService.getProductByCategory(category), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        log.info("entered in addProduct");
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") int id) throws IdNotFoundException {
        log.info("to delete the product with id "+id);
        productService.deleteProduct(id);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) throws IdNotFoundException {
        log.info("entered in update product");
        productService.updateProduct(product);
    }
}
