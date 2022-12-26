package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    static Stream<Arguments> getSaveData() {
        return Stream.of(
                Arguments.arguments(0, new Category(1, "Mobile"), false),
                Arguments.arguments(1, new Category(-1, "Mobile"), true),
                Arguments.arguments(2, new Category(1, null), true)
        );
    }

    static Stream<Arguments> getUpdateData() {
        return Stream.of(
                Arguments.arguments(0, 1, new Category(1, "Mobile"), false),
                Arguments.arguments(1, 1, new Category(-1, "Mobile"), true),
                Arguments.arguments(2, 1, new Category(1, null), true)
        );
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1, false),
                Arguments.arguments(-1, true)
        );
    }

    @Test
    void findAllCategory() {
        try {
            Mockito.when(categoryRepository.findAll()).thenReturn(getCategoryList());
            List<Category> categoryList = categoryService.findAllCategory();
            Assertions.assertEquals(getCategoryList(), categoryList);
        } catch (EntityNotFoundException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void findAllCategoryException() {
        Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            categoryService.findAllCategory();
        });
        Assertions.assertEquals("category not found", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getSaveData")
    void saveCategory(int eNumber, Category category, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.saveCategory(category);
            });
            if (eNumber == 1) {
                Assertions.assertEquals("Invalid category id", exception.getMessage());
            } else if (eNumber == 2) {
                Assertions.assertEquals("Invalid category name", exception.getMessage());
            }
        } else {
            try {
                Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(getCategory());
                Category expectedCategory = categoryService.saveCategory(category);
                Assertions.assertEquals(getCategory(), expectedCategory);

            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void saveCategoryException() {
        Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(true);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.saveCategory(new Category(1, "mobile"));
        });
        Assertions.assertEquals("category with given id already exists", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findById(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.findById(id);
            });
            Assertions.assertEquals("Invalid category id", exception.getMessage());
        } else {
            try {
                Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getCategory()));
                Optional<Category> expectedResult = categoryService.findById(id);
                Assertions.assertEquals(getCategory(), expectedResult.get());
            } catch (BadRequestException | EntityNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void findByIdException() {
        Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            categoryService.findById(233);
        });
        Assertions.assertEquals("category with given id doesn't exists", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getUpdateData")
    void update(int eNumber, int id, Category category, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.update(id, category);
            });
            if (eNumber == 1) {
                Assertions.assertEquals("Invalid category id", exception.getMessage());
            } else if (eNumber == 2) {
                Assertions.assertEquals("Invalid category name", exception.getMessage());
            }
        } else {
            try {
                Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(getCategory());
                Category expectedCategory = categoryService.update(id, category);
                Assertions.assertEquals(getCategory(), expectedCategory);

            } catch (BadRequestException | EntityNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void updateException() {
        Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            categoryService.update(123, new Category(1, "mobile"));
        });
        Assertions.assertEquals("category with given id doesn't exists", exception.getMessage());
    }

    private Category getCategory() {
        Category category = new Category(1, "Mobile");
        return category;
    }

    private List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(getCategory());
        return categoryList;
    }
}