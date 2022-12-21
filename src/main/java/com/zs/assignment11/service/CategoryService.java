package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.CategoryNotFoundException;
import com.zs.assignment11.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategory();
    Category addCategory(Category category) throws BadRequestException;

    Optional<Category> findById(int id) throws BadRequestException,  CategoryNotFoundException;

    void delete(int id) throws BadRequestException;

    Category update(Category category) throws BadRequestException, CategoryNotFoundException;
}
