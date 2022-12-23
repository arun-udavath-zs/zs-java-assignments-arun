package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is used to find all the products
     *
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public List<Product> findAllProducts() throws EntityNotFoundException {
        List<Product> productList = productRepository.findAll();
        if (productList.size() == 0) {
            throw new EntityNotFoundException("result not found");
        }
        LOGGER.info("product fetched successfully");
        return productList;
    }

    /**
     * This method is used to find the products with given category
     *
     * @param categoryId
     * @return
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    @Override
    public List<Product> findAllProductsByCategory(int categoryId) throws BadRequestException, EntityNotFoundException {
        if (categoryId <= 0) {
            throw new BadRequestException("Invalid product id");
        }
        List<Product> productList = productRepository.findAllProductsByCategory(categoryId);
        if (productList.size() == 0) {
            throw new EntityNotFoundException("product not found");
        }
        LOGGER.info("product fetched successfully");
        return productList;
    }

    /**
     * This method is used to save the product
     *
     * @param product
     * @return
     * @throws BadRequestException
     */
    @Override
    public Product saveProduct(Product product) throws BadRequestException {
        if (product.getId() <= 0)
            throw new BadRequestException("Invalid product id");
        if (product.getProductName() == null) {
            throw new BadRequestException("Invalid product name");
        }
        if (product.getPrice() < 0) {
            throw new BadRequestException("Invalid price");
        }
        if (product.getCategory().getCategoryId() <= 0) {
            throw new BadRequestException("Invalid category id");
        }
        if (productRepository.existsById(product.getId())) {
            throw new BadRequestException("product with given id already exists");
        }
        LOGGER.info("product saved successfully");
        return productRepository.save(product);
    }

    /**
     * This method is used to find the product with id
     *
     * @param id
     * @return
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    @Override
    public Optional<Product> findById(int id) throws BadRequestException, EntityNotFoundException {
        if (id <= 0) {
            throw new BadRequestException("Invalid id");
        }
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("product with given id doesn't exits");
        }
        LOGGER.info("product fetched successfully");
        return productRepository.findById(id);
    }

    /**
     * This method is used to delete the product
     *
     * @param id
     * @throws BadRequestException
     */
    @Override
    public Optional<Product> delete(int id) throws BadRequestException {
        if (id <= 0) {
            throw new BadRequestException("Invalid id");
        }
        if (!productRepository.existsById(id)) {
            throw new BadRequestException("product with given id doesn't exists");
        }
        Optional<Product> product = productRepository.findById(id);
        productRepository.deleteById(id);
        LOGGER.info("product deleted successfully");
        return product;
    }

    /**
     * This method is used to update the product
     *
     * @param product
     * @return
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    @Override
    public Product update(int id, Product product) throws BadRequestException, EntityNotFoundException {
        if (product.getId() <= 0)
            throw new BadRequestException("Invalid product id");
        if (product.getProductName() == null) {
            throw new BadRequestException("Invalid product name");
        }
        if (product.getPrice() < 0) {
            throw new BadRequestException("Invalid price");
        }
        if (product.getCategory().getCategoryId() < 0) {
            throw new BadRequestException("Invalid category id");
        }
        if (product.getCategory().getName() == null) {
            throw new BadRequestException("Invalid category name");
        }
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("product with given id doesn't exists");
        }
        return productRepository.save(product);
    }
}
