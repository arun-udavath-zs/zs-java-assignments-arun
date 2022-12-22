package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategory() throws EntityNotFoundException;
    Category saveCategory(Category category) throws BadRequestException;

    Optional<Category> findById(int id) throws BadRequestException, EntityNotFoundException;


    Category update(int id, Category category) throws BadRequestException, EntityNotFoundException;
}
