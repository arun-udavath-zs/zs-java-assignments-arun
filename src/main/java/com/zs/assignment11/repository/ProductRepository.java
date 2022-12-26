package com.zs.assignment11.repository;

import com.zs.assignment11.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product where category_id=?1", nativeQuery = true)
    List<Product> findAllProductsByCategory(int categoryId);
}
