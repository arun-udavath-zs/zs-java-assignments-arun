package com.zs.assignment11.controller;

import com.zs.assignment11.model.Product;
import com.zs.assignment11.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
   private final ProductService productService;
   ProductController(ProductService productService){
       this.productService=productService;
   }
    @GetMapping
    public List<Product> hello(){
        return productService.getProduct();
    }
    @PostMapping
    public void addProduct(@RequestBody Product product){
       productService.saveProduct(product);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") int id){
      productService.deleteProduct(id);
    }
    @PutMapping
    public void updateProduct(@RequestBody Product product){
       productService.updateProduct(product);
    }
}
