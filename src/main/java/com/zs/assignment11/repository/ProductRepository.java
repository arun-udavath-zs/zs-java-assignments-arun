package com.zs.assignment11.repository;

import com.zs.assignment11.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {


}
