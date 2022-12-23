package com.zs.assignment11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.service.ProductServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllProducts(){
        try {
            BDDMockito.given(productService.findAllProducts()).willReturn(getProductList());
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/products")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void getProductById() {
        try {
            BDDMockito.given(productService.findById(ArgumentMatchers.anyInt())).willReturn(Optional.of(getProduct()));
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/products/{id}", 1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getProductByCategory() {
        try {
            BDDMockito.given(productService.findAllProductsByCategory(ArgumentMatchers.anyInt())).willReturn(getProductList());
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/products/category/{categoryId}", 1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void addProduct() {
        try {
            String jsonProduct = objectMapper.writeValueAsString(getProduct());
            BDDMockito.given(productService.saveProduct(ArgumentMatchers.any(Product.class))).willReturn(getProduct());
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonProduct)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void deleteProduct() {
        try {
            BDDMockito.given(productService.delete(ArgumentMatchers.anyInt())).willReturn(Optional.of(getProduct()));
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/products/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("iphone")));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateProduct()  {
        try {
            String jsonProduct = objectMapper.writeValueAsString(getProduct());
            BDDMockito.given(productService.update(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Product.class))).willReturn(getProduct());
            mockMvc.perform(MockMvcRequestBuilders
                            .put("/api/products/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonProduct)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("iphone")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
