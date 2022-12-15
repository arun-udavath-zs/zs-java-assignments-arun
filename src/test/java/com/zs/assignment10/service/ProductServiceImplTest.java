package com.zs.assignment10.service;

import com.zs.assignment10.dao.ProductDao;
import com.zs.assignment10.exception.DatabaseConnectionFailedException;
import com.zs.assignment10.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductDao productDao;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void findAll() throws DatabaseConnectionFailedException, SQLException {
        Mockito.when(productDao.findAll(Mockito.anyString())).thenReturn(dummyProductList());
        List<Product> productList = productServiceImpl.findAll("product");
        Assertions.assertEquals(1, productList.size());
    }

    @Test
    void findById() throws DatabaseConnectionFailedException, SQLException {
        Mockito.when(productDao.findById(Mockito.anyInt(), Mockito.anyString())).thenReturn(dummyProduct());
        Product actualProduct = productServiceImpl.findById(1, "product");
        Assertions.assertEquals("iphone", actualProduct.getProductName());
    }

    @Test
    void insert() throws DatabaseConnectionFailedException, SQLException {
        Mockito.verify(productDao.insert((Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()), Mockito.times(1));
        productServiceImpl.insert(1, "iphone", 120000, "product");
    }

    @Test
    void deleteById() throws DatabaseConnectionFailedException, SQLException {
        Mockito.verify(productDao.deleteById((Mockito.anyInt(), Mockito.anyString()), Mockito.times(1));
        productServiceImpl.deleteById(1, "product");
    }

    @Test
    void exist() throws DatabaseConnectionFailedException {
        Mockito.when(productDao.exist(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);
        boolean actualResult = productServiceImpl.exist(1, "product");
        Assertions.assertEquals("true", actualResult);
    }

    @Test
    void tableCreation() throws DatabaseConnectionFailedException {
        Mockito.verify(productDao.tableCreation(), Mockito.times(1));
        productServiceImpl.tableCreation();
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