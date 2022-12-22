package com.zs.assignment11.controller;

import com.zs.assignment11.model.Category;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductServiceImpl productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ProductController.class).build();
    }

    @Test
    void getAllProducts() throws Exception {
            Mockito.when(productService.findAllProducts()).thenReturn(getProductList());

            mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/products")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].getId()",is(1)));

    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByCategory() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(getProduct());
        return productList;
    }
    private Product getProduct() {
        Product product = new Product(1, "iphone", 120000, new Category(1, "mobile"));
        return product;
    }
}
