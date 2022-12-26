package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * This method is used to find all the category
     *
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public List<Category> findAllCategory() throws EntityNotFoundException {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.size() == 0) {
            throw new EntityNotFoundException("category not found");
        }
        LOGGER.info("category fetched successfully");
        return categoryList;
    }


    /**
     * This method is used to save the category
     *
     * @param category
     * @return
     * @throws BadRequestException
     */
    @Override
    public Category saveCategory(Category category) throws BadRequestException {
        if (category.getCategoryId() <= 0) {
            throw new BadRequestException("Invalid category id");
        }
        if (category.getName() == null) {
            throw new BadRequestException("Invalid category name");
        }
        if (categoryRepository.existsById(category.getCategoryId())) {
            throw new BadRequestException("category with given id already exists");
        }
        LOGGER.info("category saved successfully");
        return categoryRepository.save(category);
    }

    /**
     * This method is used to find the category by id
     *
     * @param id
     * @return
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    @Override
    public Optional<Category> findById(int id) throws BadRequestException, EntityNotFoundException {
        if (id <= 0) {
            throw new BadRequestException("Invalid category id");
        }
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("category with given id doesn't exists");
        }
        LOGGER.info("category fetched successfully");
        return categoryRepository.findById(id);
    }

    /**
     * This method is used to update the product
     *
     * @param id
     * @param category
     * @return
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    @Override
    public Category update(int id, Category category) throws BadRequestException, EntityNotFoundException {
        if (category.getCategoryId() <= 0) {
            throw new BadRequestException("Invalid category id");
        }
        if (category.getName() == null) {
            throw new BadRequestException("Invalid category name");
        }
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("category with given id doesn't exists");
        }
        LOGGER.info("category updated successfully");
        return categoryRepository.save(category);
    }

}
