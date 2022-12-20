package com.zs.assignment11.controller;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAllCategory() {
        return new ResponseEntity<>(categoryService.findAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(categoryService.findById(id), HttpStatus.CREATED);
        } catch (BadRequestException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<Category>(categoryService.addCategory(category), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        try {
            categoryService.delete(id);
        } catch (BadRequestException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @PutMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
