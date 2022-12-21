package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.CategoryNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
                Arguments.arguments(new Category(1,"Mobile"), false),
                Arguments.arguments(new Category(-1,"Mobile"), true),
                Arguments.arguments(new Category(1,null),true)
        );
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1, false),
                Arguments.arguments(-1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("getSaveData")
    void addCategory(Category category, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.update(category);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(getCategory());
                Category expectedCategory =categoryService.addCategory(category);
                Assertions.assertEquals(getCategory(),expectedCategory);

            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findById(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.findById(id);
            });
            Assertions.assertEquals("Id cannot be negative", exception.getMessage());
        } else {
            try {
                Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getCategory()));
               categoryService.findById(id);
            } catch (BadRequestException | CategoryNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    void delete(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                categoryService.delete(id);
            });
            Assertions.assertEquals("Id cannot be negative", exception.getMessage());
        } else {
            try {
               categoryService.delete(id);
               Mockito.verify(categoryRepository,Mockito.times(1)).deleteById(ArgumentMatchers.anyInt());
            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getSaveData")
    void update(Category category, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                 categoryService.update(category);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(getCategory());
                Category expectedCategory = categoryService.update(category);
                Assertions.assertEquals(getCategory(), expectedCategory);
            } catch (BadRequestException | CategoryNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    private Category getCategory() {
        Category category = new Category(1, "Mobile");
        return category;
    }
}