package com.zs.assignment11.service;

import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void getAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(dummyProductList());
        List<Product> actualResult= productServiceImpl.getAllProducts();
        assertEquals("redmi",actualResult.get(0).getProductName());
    }

    @Test
    void getProductById() {
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(dummyProduct()));
        Product actualResult= productServiceImpl.getProductById(1);
        assertEquals("redmi",actualResult.getProductName());
    }

    @Test
    void getAllCategories() {
        Mockito.when(productRepository.getAllCategories()).thenReturn(dummyCategory());
        List<String> actualResult= productServiceImpl.getAllCategories();
        assertEquals(2,actualResult.size());
    }

    @Test
    void getProductByCategory() {
        Mockito.when(productRepository.getProductByCategory(Mockito.anyString())).thenReturn(dummyProductList());
        List<Product> actualResult= productServiceImpl.getProductByCategory("Mobile");
        assertEquals(1,actualResult.size());
        assertEquals(20000,actualResult.get(0).getPrice());
    }

    @Test
    void saveProduct() {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(dummyProduct());
        Product actualProduct= productServiceImpl.saveProduct(new Product(1,"iphone","Mobiles",120000));
        assertEquals("redmi",actualProduct.getProductName());
    }

    private List<Product> dummyProductList(){
        List<Product> productList = new ArrayList<>();
        productList.add(dummyProduct());
        return productList;
    }
    private Product dummyProduct(){
        Product product= new Product(1,"redmi","Mobiles",20000);
        return product;
    }
    private List<String> dummyCategory(){
        List<String> list= new ArrayList<>();
        list.add("Mobile");
        list.add("Groceries");
        return list;
    }
}