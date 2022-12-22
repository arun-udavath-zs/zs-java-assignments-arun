package com.zs.assignment11.service;

import com.zs.assignment11.exception.BadRequestException;
import com.zs.assignment11.exception.EntityNotFoundException;
import com.zs.assignment11.model.Category;
import com.zs.assignment11.model.Product;
import com.zs.assignment11.repository.CategoryRepository;
import com.zs.assignment11.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    static Stream<Arguments> getSaveData() {
        return Stream.of(
                Arguments.arguments(0, new Product(1, "iphone", 12000, new Category(1, "mobile")), false),
                Arguments.arguments(1, new Product(-1, "iphone", 12000, new Category(1, "mobile")), true),
                Arguments.arguments(2, new Product(1, null, 12000, new Category(1, "mobile")), true),
                Arguments.arguments(3, new Product(1, "iphone", -1, new Category(1, "mobile")), true),
                Arguments.arguments(4, new Product(1, "iphone", 100, new Category(-1, "mobile")), true),
                Arguments.arguments(5, new Product(1, "iphone", 100, new Category(1, null)), true)
        );
    }

    static Stream<Arguments> getUpdateData() {
        return Stream.of(
                Arguments.arguments(0, 1, new Product(1, "iphone", 12000, new Category(1, "mobile")), false),
                Arguments.arguments(1, 1, new Product(-1, "iphone", 12000, new Category(1, "mobile")), true),
                Arguments.arguments(2, 1, new Product(1, null, 12000, new Category(1, "mobile")), true),
                Arguments.arguments(3, 1, new Product(1, "iphone", -1, new Category(1, "mobile")), true),
                Arguments.arguments(4, 1, new Product(1, "iphone", 100, new Category(-1, "mobile")), true),
                Arguments.arguments(5, 1, new Product(1, "iphone", 100, new Category(1, null)), true)
        );
    }

    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1, false),
                Arguments.arguments(-1, true)
        );
    }

    @Test
    void findAllProducts() {
        try {
            Mockito.when(productRepository.findAll()).thenReturn(getProductList());
            List<Product> productList = productService.findAllProducts();
            Assertions.assertEquals(getProductList(), productList);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAllProductsException() {
        Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>());
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.findAllProducts();
        });
        Assertions.assertEquals("result not found", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findAllProductsByCategory(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.findAllProductsByCategory(id);
            });
            Assertions.assertEquals("Invalid product id", exception.getMessage());
        } else {
            try {
                Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(productRepository.findAllProductsByCategory(Mockito.anyInt())).thenReturn(getProductList());
                List<Product> productList = productService.findAllProductsByCategory(id);
                Assertions.assertEquals(1, productList.size());
            } catch (BadRequestException | EntityNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void findAllByCategoryEntityException() {
        Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(productRepository.findAllProductsByCategory(1)).thenReturn(new ArrayList<>());
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.findAllProductsByCategory(1);
        });
        Assertions.assertEquals("result not found", exception.getMessage());
    }

    @Test
    void findAllByCategoryBadRequestException() {
        Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(false);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            productService.findAllProductsByCategory(1);
        });
        Assertions.assertEquals("category with given id doesn't exists", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getSaveData")
    void saveProduct(int eNumber, Product product, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.saveProduct(product);
            });
            if (eNumber == 1) {
                Assertions.assertEquals("Invalid product id", exception.getMessage());
            }
            if (eNumber == 2) {
                Assertions.assertEquals("Invalid product name", exception.getMessage());
            }
            if (eNumber == 3) {
                Assertions.assertEquals("Invalid price", exception.getMessage());
            }
            if (eNumber == 4) {
                Assertions.assertEquals("Invalid category id", exception.getMessage());
            }
            if (eNumber == 5) {
                Assertions.assertEquals("Invalid category name", exception.getMessage());
            }
        } else {
            try {
                Mockito.when(productRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
                Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(getProduct());
                Product actualProduct = productService.saveProduct(product);
                Assertions.assertEquals(getProduct(), actualProduct);
            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void saveProductException() {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            productService.saveProduct(getProduct());
        });
        Assertions.assertEquals("product with given id already exists", exception.getMessage());
    }

    @Test
    void saveProductCategoryException() {
        Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(false);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            productService.saveProduct(getProduct());
        });
        Assertions.assertEquals("category with given id doesn't exists", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findById(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.findById(id);
            });
            Assertions.assertEquals("Invalid id", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
                Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getProduct()));
                Optional<Product> actualProduct = productService.findById(id);
                Assertions.assertEquals(Optional.of(getProduct()), actualProduct);
            } catch (BadRequestException | EntityNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void findByIdException() {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(false);
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.findById(1);
        });
        Assertions.assertEquals("product with given id doesn't exits", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void delete(int id, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.delete(id);
            });
            Assertions.assertEquals("Invalid id", exception.getMessage());
        } else {
            try {
                Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
                Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getProduct()));
                Optional<Product> expectedProduct = productService.delete(id);
                Mockito.verify(productRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyInt());
                Assertions.assertEquals(getProduct(), expectedProduct.get());
            } catch (BadRequestException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void deleteException() {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(false);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            productService.delete(1);
        });
        Assertions.assertEquals("product with given id doesn't exists", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getUpdateData")
    void update(int eNumber, int id, Product product, boolean isExcepted) {
        if (isExcepted) {
            BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
                productService.update(id, product);
            });
            if (eNumber == 1) {
                Assertions.assertEquals("Invalid product id", exception.getMessage());
            }
            if (eNumber == 2) {
                Assertions.assertEquals("Invalid product name", exception.getMessage());
            }
            if (eNumber == 3) {
                Assertions.assertEquals("Invalid price", exception.getMessage());
            }
            if (eNumber == 4) {
                Assertions.assertEquals("Invalid category id", exception.getMessage());
            }
            if (eNumber == 5) {
                Assertions.assertEquals("Invalid category name", exception.getMessage());
            }
        } else {
            try {
                Mockito.when(productRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
                Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(getProduct());
                Product actualProduct = productService.update(id, product);
                Assertions.assertEquals(getProduct(), actualProduct);
            } catch (BadRequestException | EntityNotFoundException e) {
                Assertions.fail(e.getMessage());
            }
        }
    }

    @Test
    void UpdateException() {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(false);
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.update(1, getProduct());
        });
        Assertions.assertEquals("product with given id doesn't exists", exception.getMessage());
    }

    @Test
    void updateCategoryException() {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(false);
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> {
            productService.update(1, getProduct());
        });
        Assertions.assertEquals("category with given id doesn't exists", exception.getMessage());
    }

    private List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(getProduct());
        return productList;
    }

    private Product getProduct() {
        Product product = new Product(1, "iphone", 120000, new Category(1, "mobile"));
        return product;
    }
}