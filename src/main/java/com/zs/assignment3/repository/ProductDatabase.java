package com.zs.assignment3.repository;

import com.zs.assignment3.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDatabase {
    private HashMap<ProductType, List<String>> productData = new HashMap<>();

    public ProductDatabase() {
        initialDatabase();
    }


    public void addProduct(ProductType product, String productType) {

        List<String> productTypeList = productData.get(product);
        productTypeList.add(productType);
        productData.put(product, productTypeList);
        System.out.println(productType + " added to the Database.\nThanks for adding the product!!\n");
    }

    public void readProduct(ProductType product) {
        List<String> productTypeList = productData.get(product);
        for (String item : productTypeList) {
            System.out.println(item);
        }
    }

    public void updateProduct(ProductType product, String prevProduct, String productType) {
        List<String> productTypeList = productData.get(product);
        boolean flag = false;
        for (int i = 0; i < productTypeList.size(); i++) {
            if (productTypeList.get(i).equals(prevProduct)) {
                productTypeList.add(i, productType);
                productData.put(product, productTypeList);
                System.out.println("Thanks for updating the product from " + prevProduct + " to " + productType + "!!\n");
                flag = true;
                break;
            }
        }
        if (!flag) System.out.println(productType + " product not found in the database");
    }

    public void deleteProduct(ProductType product, String productType) {
        List<String> productTypeList = productData.get(product);
        boolean flag = false;
        for (String item : productTypeList) {
            if (item.equals(productType)) {
                productTypeList.remove(item);
                productData.put(product, productTypeList);
                System.out.println(productType + " deleted from the Database.\n");
                flag = true;
                break;
            }
        }
        if (!flag) System.out.println(productType + " product not found in the database");    }

    private void initialDatabase() {
        List<String> fashionProducts = new ArrayList<>();
        fashionProducts.add("Jeans");
        fashionProducts.add("Hoodies");
        fashionProducts.add("shirts");
        productData.put(ProductType.FASHION, fashionProducts);
        List<String> electronicsProducts = new ArrayList<>();
        electronicsProducts.add("phones");
        electronicsProducts.add("laptops");
        electronicsProducts.add("washingMachine");
        productData.put(ProductType.ELECTRONIC, electronicsProducts);
        List<String> groceryProducts = new ArrayList<>();
        groceryProducts.add("dryFruits");
        groceryProducts.add("vegetables");
        groceryProducts.add("rice");
        productData.put(ProductType.GROCERY, groceryProducts);
        List<String> medicines = new ArrayList<>();
        medicines.add("dolo");
        medicines.add("paracetamol");
        medicines.add("aspirin");
        productData.put(ProductType.MEDICINE, medicines);

    }
}
