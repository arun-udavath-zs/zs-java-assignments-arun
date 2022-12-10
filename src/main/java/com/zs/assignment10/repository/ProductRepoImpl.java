package com.zs.assignment10.repository;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.util.ResultToProductConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepoImpl.class);
    private final DatabaseConnection databaseConnection;
    private final ResultToProductConversion resultToProductConversion;

    public ProductRepoImpl() {
        this.databaseConnection = new DatabaseConnection();
        this.resultToProductConversion = new ResultToProductConversion();
    }

    /**
     * used to find the data in the database
     *
     * @param tableName input parameter to find the database
     * @return list of products in the database
     */
    @Override
    public List<Product> findAll(String tableName) {
        String findAllQuery = "SELECT * FROM " + tableName + ";";

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultToProductConversion.listConversion(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * used to find the product by id
     *
     * @param id        id of the product to find
     * @param tableName table name
     * @return the product of given id
     */
    @Override
    public Product findById(int id, String tableName) {
        String findByIdQuery = "SELECT * from " + tableName + " where id= " + id + ";";

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultToProductConversion.productConversion(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * used to insert the date in the database
     *
     * @param id          product id
     * @param productName product name to insert
     * @param price       price to insert
     * @param tableName   table name
     */
    @Override
    public void insert(int id, String productName, int price, String tableName) {
        String addProductQuery = "INSERT INTO " + tableName + "(id,product_name,price) VALUES(?,?,?)";

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addProductQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, productName);
            preparedStatement.setInt(3, price);
            preparedStatement.executeUpdate();
            logger.info("product added in the database successfully!!");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * used to update the product in the database
     *
     * @param id          product it
     * @param productName product name
     * @param price       product price
     * @param tableName   table name
     */
    @Override
    public void update(int id, String productName, int price, String tableName) {
        String updateProductQuery = "UPDATE " + tableName + " SET product_name= ?, price= ? where id= " + id + ";";

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateProductQuery)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, price);
            preparedStatement.executeUpdate();
            logger.info("product updated in the database!!");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * used to delete the product by id in the database
     *
     * @param id        product id
     * @param tableName table name
     */
    @Override
    public void deleteById(int id, String tableName) {
        String updateProductQuery = "DELETE FROM " + tableName + " where id= " + id + ";";

        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateProductQuery)) {

            preparedStatement.executeUpdate();
            logger.info("product deleted successfully!!");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean exist(int id, String tableName) {
        String existQuery = "SELECT * FROM " + tableName + " where id=?";
        try (Connection connection = databaseConnection.dbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(existQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return false;
    }

}
