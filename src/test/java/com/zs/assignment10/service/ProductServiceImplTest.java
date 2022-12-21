package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDaoImpl;
import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;
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
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductDaoImpl productDao;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    static Stream<Arguments> getInsertData() {
        return Stream.of(
                Arguments.arguments(1, "iphone", 120000, "product", false),
                Arguments.arguments(-1, "iphone", 120000, "product", true),
                Arguments.arguments(1, null, 120000, "product", true),
                Arguments.arguments(1, "iphone", -1, "product", true),
                Arguments.arguments(1, "iphone", 120000, null, true)
        );
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1, "product", false),
                Arguments.arguments(-1, "product", true),
                Arguments.arguments(1, null, true)
        );
    }

    static Stream<Arguments> getFindAll() {
        return Stream.of(
                Arguments.arguments("product", false),
                Arguments.arguments(null, true)
        );
    }


    @ParameterizedTest
    @MethodSource("getFindAll")
    void findAll(String tableName, boolean isExpected) {
        if (isExpected) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productServiceImpl.findAll(tableName);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                List<Product> productList = getProductList();
                Mockito.when(productDao.findAll(Mockito.anyString())).thenReturn(productList);
                Assertions.assertEquals(productList, productServiceImpl.findAll(tableName));
            } catch (InternalServerException | BadRequestException e) {
                Assertions.fail(e.getMessage());
            }

        }
    }


    @ParameterizedTest
    @MethodSource("getData")
    void findById(int id, String tableName, boolean isExpected) {
        if (isExpected) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productServiceImpl.findById(id, tableName);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(productDao.findById(Mockito.anyInt(), Mockito.anyString())).thenReturn(getProduct());
                Product actualProduct = productServiceImpl.findById(id, tableName);
                Assertions.assertEquals("iphone", actualProduct.getProductName());
            } catch (InternalServerException | BadRequestException e) {
                Assertions.fail(e.getMessage());
            }

        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    void delete(int id, String tableName, boolean isExpected) {
        if (isExpected) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productServiceImpl.deleteById(id, tableName);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                productServiceImpl.deleteById(id, tableName);
                Mockito.verify(productDao, Mockito.times(1)).deleteById(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString());
            } catch (InternalServerException | BadRequestException e) {
                Assertions.fail(e.getMessage());
            }

        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    void exist(int id, String tableName, boolean isExpected) {
        if (isExpected) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productServiceImpl.exist(id, tableName);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                Mockito.when(productDao.exist(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);
                Assertions.assertTrue(productServiceImpl.exist(1, "product"));
            } catch (InternalServerException | BadRequestException e) {
                Assertions.fail(e.getMessage());
            }

        }
    }

    @Test
    void tableCreation() {
        try {
            productServiceImpl.createTable();
            Mockito.verify(productDao, Mockito.times(1)).createTable();
        } catch (InternalServerException e) {
            throw new RuntimeException(e);
        }

    }

    @ParameterizedTest
    @MethodSource("getInsertData")
    void insert(int id, String productName, int price, String tableName, boolean isExpected) {
        if (isExpected) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productServiceImpl.insert(id, productName, price, tableName);
            });
            Assertions.assertEquals("given input is not valid", exception.getMessage());
        } else {
            try {
                productServiceImpl.insert(id, productName, price, tableName);
                Mockito.verify(productDao, Mockito.times(1)).insert(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString()
                        , ArgumentMatchers.anyInt(), ArgumentMatchers.anyString());
            } catch (InternalServerException | BadRequestException e) {
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
        Product product = new Product();
        product.setId(1);
        product.setProductName("iphone");
        product.setPrice(120000);
        return product;
    }
}