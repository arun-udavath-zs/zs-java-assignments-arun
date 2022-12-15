package com.zs.assignment10.util;

import com.zs.assignment10.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultToProductConversion {
    List<Product> productList = new ArrayList<>();

    public List<Product> toListConversion(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getInt("price"));
            productList.add(product);
        }
        return productList;
    }

    public Product toProductConversion(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        while (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getInt("price"));
        }
        return product;
    }
}
