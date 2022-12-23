package com.zs.assignment11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.service.CategoryServiceImpl;
import org.hamcrest.Matchers;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @MockBean
    private CategoryServiceImpl categoryService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllCategory() throws Exception {

        BDDMockito.given(categoryService.findAllCategory()).willReturn(getCategoryList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].categoryId", Matchers.is(1)));

    }

    @Test
    void findById() throws Exception {
        BDDMockito.given(categoryService.findById(ArgumentMatchers.anyInt())).willReturn(Optional.of(getCategory()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/categories/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mobile")));

    }

    @Test
    void addCategory() throws Exception {
        String jsonProduct = objectMapper.writeValueAsString(getCategory());
        BDDMockito.given(categoryService.saveCategory(ArgumentMatchers.any(Category.class))).willReturn(getCategory());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProduct)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mobile")));
    }

    @Test
    void updateCategory() throws Exception {
        String jsonProduct = objectMapper.writeValueAsString(getCategory());
        BDDMockito.given(categoryService.update(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Category.class))).willReturn(getCategory());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/categories/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProduct)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mobile")));
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