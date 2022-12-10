package com.zs.assignment11.service;

import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        Optional<Product> productOptional= productRepository.findById(product.getId());
        if (productOptional.isPresent()){
            throw new IllegalStateException("Id already exists");
        }
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        boolean exits= productRepository.existsById(id);
        if (!exits)
            throw new IllegalStateException("product with id "+ id+" does not exists");
      productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
       boolean exists= productRepository.existsById(product.getId());
       if (!exists)
           throw new IllegalStateException("id doesn't exits");
       productRepository.save(product);
    }

}
