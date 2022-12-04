package com.zs.assignment3.repository;

import com.zs.assignment3.controller.ProductController;
import com.zs.assignment3.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDatabase {
    private static HashMap<ProductType, List<String>> productData = new HashMap<>();
    ProductController productController = new ProductController();

    public void addProduct(ProductType product, String productType) {

        List<String> productTypeList = productData.get(product);
        productTypeList.add(productType);
        productData.put(product, productTypeList);
        System.out.println(productType + " added to the Database.\nThanks for adding the product!!\n");
        homePage();
    }

    public void readProduct(ProductType product) {
        List<String> productTypeList = productData.get(product);
        for (String item : productTypeList) {
            System.out.println(item);
        }
        homePage();
    }

    public void updateProduct(ProductType product, String prevProduct, String productType) {
        List<String> productTypeList = productData.get(product);
        boolean flag = false;
        for (int i = 0; i < productTypeList.size(); i++) {
            if (productTypeList.get(i).equals(prevProduct)) {
                productTypeList.add(i, productType);
                productData.put(product, productTypeList);
                System.out.println("Thanks for updating the product!!\n");
                flag = true;
                break;
            }
        }
        if (!flag) System.out.println(productType + " product not found in the database");
        homePage();
    }

    public void deleteProduct(ProductType product, String productType) {
        List<String> productTypeList = productData.get(product);
        boolean flag = false;
        for (String item : productTypeList) {
            if (item.equals(productType)) {
                productTypeList.remove(productType);
                productData.put(product, productTypeList);
                System.out.println(productType + " deleted from the Database.\n");
                flag = true;
            }
        }
        if (!flag) System.out.println(productType + " product not found in the database");
        homePage();
    }

    public void initialDatabase() {
        List<String> fashionProducts = new ArrayList<>();
        fashionProducts.add("Jeans");
        fashionProducts.add("Hoodies");
        productData.put(ProductType.FASHION, fashionProducts);
        List<String> electronicsProducts = new ArrayList<>();
        electronicsProducts.add("phones");
        electronicsProducts.add("laptops");
        productData.put(ProductType.ELECTRONIC, electronicsProducts);
        List<String> groceryProducts = new ArrayList<>();
        groceryProducts.add("dryFruits");
        groceryProducts.add("vegetables");
        productData.put(ProductType.GROCERY, groceryProducts);
        List<String> medicines = new ArrayList<>();
        medicines.add("dolo");
        medicines.add("paracetamol");
        productData.put(ProductType.MEDICINE, medicines);
    }

    public void homePage() {
        productController.controller();
    }
}
