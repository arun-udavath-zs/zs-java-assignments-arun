package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.ProductNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
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
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    static Stream<Arguments> getSaveData() {
        return Stream.of(
                Arguments.arguments(new Product(1, "iphone", 12000, 1), false),
                Arguments.arguments(new Product(-1, "iphone", 12000, 1), true),
                Arguments.arguments(new Product(1, null, 12000, 1), true),
                Arguments.arguments(new Product(1, "iphone", 12000, -1), true)
        );
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1, false),
                Arguments.arguments(-1, true)
        );
    }

    @Test
    void findAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(getProductList());
        List<Product> productList = productService.findAllProducts();
        Assertions.assertEquals(1, productList.size());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findAllProductsByCategory(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.findAllProductsByCategory(id);
            });
            Assertions.assertEquals("Category id cannot be negative", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.findAllProductsByCategory(Mockito.anyInt())).thenReturn(getProductList());
                List<Product> productList = productService.findAllProductsByCategory(id);
                Assertions.assertEquals(1, productList.size());
            } catch (BadRequestException | ProductNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getSaveData")
    void saveProduct(Product product, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.saveProduct(product);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(getProduct());
                Product actualProduct = productService.saveProduct(product);
                Assertions.assertEquals(getProduct(), actualProduct);
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
                productService.findById(id);
            });
            Assertions.assertEquals("Id cannot be negative", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getProduct()));
                Optional<Product> actualProduct = productService.findById(id);
                Assertions.assertEquals(Optional.of(getProduct()), actualProduct);
            } catch (BadRequestException | ProductNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    void delete(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.delete(id);
            });
            Assertions.assertEquals("Id cannot be negative", exception.getMessage());
        } else {
            try {
                productService.delete(id);
                Mockito.verify(productRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyInt());
            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }


    @ParameterizedTest
    @MethodSource("getSaveData")
    void update(Product product, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.update(product);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(getProduct());
                Product actualProduct = productService.update(product);
                Assertions.assertEquals(getProduct(), actualProduct);

            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(getProduct());
        return productList;
    }

    private Product getProduct() {
        Product product = new Product(1, "iphone", 120000, 1);
        return product;
    }
}