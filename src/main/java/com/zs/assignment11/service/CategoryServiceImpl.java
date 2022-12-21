package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.CategoryNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.repository.CategoryRepository;
import com.zs.assignment11.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * This method is used to add the category
     * @param category
     * @return
     */
    @Override
    public Category addCategory(Category category) throws BadRequestException {
        if (category.getCategoryId() < 0 | category.getName() == null)
            throw new BadRequestException("given input is not valid");
        if (categoryRepository.existsById(category.getCategoryId()))
            throw new BadRequestException("category with given id already exists");
        return categoryRepository.save(category);
    }

    /**
     * This method is used to find the category by id
     * @param id
     * @return
     */
    @Override
    public Optional<Category> findById(int id) throws BadRequestException, CategoryNotFoundException {
        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException("category with given id doesn't exists");
        return categoryRepository.findById(id);
    }

    /**
     * This method is used to update the product
     * @param id
     */
    @Override
    public void delete(int id) throws BadRequestException {
        if (id < 0)
            throw new BadRequestException("Id cannot be negative");
        categoryRepository.deleteById(id);
    }

    /**
     * This method is used to update the product
     * @param category
     * @return
     */
    @Override
    public Category update(Category category) throws BadRequestException, CategoryNotFoundException {
        if (category.getCategoryId() < 0 || category.getName() == null)
            throw new BadRequestException("given input is not valid");
        if (!categoryRepository.existsById(category.getCategoryId()))
            throw new CategoryNotFoundException("category with given id doesn't exists");
        return categoryRepository.save(category);
    }

}
