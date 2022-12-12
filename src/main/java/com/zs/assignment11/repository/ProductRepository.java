package com.zs.assignment11.repository;

import com.zs.assignment11.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getProductByCategory(String category);

    @Query(value = "SELECT DISTINCT category from product", nativeQuery = true)
    List<String> getAllCategories();
}
