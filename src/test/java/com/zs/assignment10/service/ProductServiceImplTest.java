package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDaoImpl;
import com.zs.assignment10.exception.BadRequestException;
import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductDaoImpl productDao;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void findAll() {
        try {
            List<Product> productList = dummyProductList();
            Mockito.when(productDao.findAll(Mockito.anyString())).thenReturn(productList);
            Assertions.assertEquals(productList, productServiceImpl.findAll("product"));
        } catch (InternalServerException | BadRequestException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void findById() {
        try {
            Mockito.when(productDao.findById(Mockito.anyInt(), Mockito.anyString())).thenReturn(dummyProduct());
            Product actualProduct = productServiceImpl.findById(1, "product");
            Assertions.assertEquals("iphone", actualProduct.getProductName());
        } catch (InternalServerException | BadRequestException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void insert() {
        try {
            productServiceImpl.insert(1, "iphone", 120000, "product");
            Mockito.verify(productDao, Mockito.times(1)).insert(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString()
                    , ArgumentMatchers.anyInt(), ArgumentMatchers.anyString());
        } catch (InternalServerException | BadRequestException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void deleteById() {
        try {
            productServiceImpl.deleteById(1, "product");
            Mockito.verify(productDao, Mockito.times(1)).deleteById(ArgumentMatchers.anyInt(), ArgumentMatchers.anyString());
        } catch (InternalServerException | BadRequestException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void exist() {
        try {
            Mockito.when(productDao.exist(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);
            Assertions.assertTrue(productServiceImpl.exist(1, "product"));
        } catch (InternalServerException | BadRequestException e) {
            Assertions.fail(e.getMessage());
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

    private List<Product> dummyProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(dummyProduct());
        return productList;
    }

    private Product dummyProduct() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("iphone");
        product.setPrice(120000);
        return product;
    }
}