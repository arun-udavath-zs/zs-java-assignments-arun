package com.zs.assignment10.dao;

import com.zs.assignment10.exception.InternalServerException;
import com.zs.assignment10.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final ConnectionManager databaseConnection;
    private String dbUrl;
    private String userName;
    private String password;

    public ProductDaoImpl() {
        this.databaseConnection = new ConnectionManager();
        fetchProperties();
    }

    private void fetchProperties() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            String homeDir = System.getProperty("user.dir");
            fileInputStream = new FileInputStream(homeDir + "/src/main/resources/application.properties");
            properties.load(fileInputStream);
            dbUrl = properties.getProperty("DB_URL");
            userName = properties.getProperty("USER");
            password = properties.getProperty("PASSWORD");

        } catch (IOException e) {
            LOGGER.error("application properties file is incorrect");
        }
    }

    /**
     * used to find the data in the database
     *
     * @param tableName input parameter to find the database
     * @return list of products in the database
     */
    @Override
    public List<Product> findAll(String tableName) throws InternalServerException {
        String fetchAllQuery = "SELECT * FROM " + tableName + ";";

        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(fetchAllQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return toList(resultSet);
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    /**
     * This method is used to find the product by id
     *
     * @param id        id of the product to find
     * @param tableName table name
     * @return the product of given id
     */
    @Override
    public Product findById(int id, String tableName) throws InternalServerException {
        String findQuery = "SELECT * from " + tableName + " where id= " + id + ";";

        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return toProduct(resultSet);
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    /**
     * This method is used to insert the date in the database
     *
     * @param id          product id
     * @param productName product name to insert
     * @param price       price to insert
     * @param tableName   table name
     */
    @Override
    public void insert(int id, String productName, int price, String tableName) throws InternalServerException {
        String insertQuery = "INSERT INTO " + tableName + "(id,product_name,price) VALUES(?,?,?)";

        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, productName);
            preparedStatement.setInt(3, price);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    /**
     * This method used to update the product in the database
     *
     * @param id          product it
     * @param productName product name
     * @param price       product price
     * @param tableName   table name
     */
    @Override
    public void update(int id, String productName, int price, String tableName) throws InternalServerException {
        String updateQuery = "UPDATE " + tableName + " SET product_name= ?, price= ? where id= " + id + ";";

        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, price);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    /**
     * This method is used to delete the product by id in the database
     *
     * @param id        product id
     * @param tableName table name
     */
    @Override
    public void deleteById(int id, String tableName) throws InternalServerException {
        String deleteQuery = "DELETE FROM " + tableName + " where id= " + id + ";";

        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    /**
     * This method is used to check if the product exists with id
     *
     * @param id        product id
     * @param tableName table name
     * @return
     * @throws InternalServerException
     */
    @Override
    public boolean exist(int id, String tableName) throws InternalServerException {
        String existQuery = "SELECT * FROM " + tableName + " where id=?";
        try (Connection connection = databaseConnection.getDbConnection(dbUrl, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(existQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }
        return false;
    }

    /**
     * This method is used to create the table
     *
     * @throws InternalServerException
     */
    @Override
    public void createTable() throws InternalServerException {
        ConnectionManager dbConnection = new ConnectionManager();

        try (Connection connection = dbConnection.getDbConnection(dbUrl, userName, password);
             Statement statement = connection.createStatement()) {
            String createQuery = "CREATE TABLE IF NOT EXISTS products (id int, product_name VARCHAR(20), price int)";
            statement.executeUpdate(createQuery);
        } catch (SQLException e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    private List<Product> toList(ResultSet resultSet) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getInt("price"));
            productList.add(product);
        }
        return productList;
    }

    private Product toProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        while (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getInt("price"));
        }
        return product;
    }
}
