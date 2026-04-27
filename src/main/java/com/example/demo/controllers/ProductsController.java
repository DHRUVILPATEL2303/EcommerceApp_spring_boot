package com.example.demo.controllers;

import com.example.demo.dto.ProductWIthCategoryDTO;
import com.example.demo.dto.ProductsDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.services.IProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    private final IProductsService productsService;

    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<ProductsDTO> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductsDTO getProductById(@PathVariable Long id) throws Exception {
        return productsService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productDTO) {
        ProductsDTO created = productsService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct(@PathVariable Long id, @RequestBody ProductsDTO productDTO) throws Exception {
        ProductsDTO updated = productsService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{id}")
    public ProductWIthCategoryDTO productWithCategory(@PathVariable Long id) throws Exception {
        return productsService.productWithCategory(id);
    }

    @GetMapping("/expensive/{minprice}")
    public List<ProductsDTO> getExpensiveProducts(@PathVariable("minprice") Long minPrice) {
        return productsService.getExpensiveProducts(minPrice);
    }

    @GetMapping("/search/{productname}")
    public List<ProductsDTO> getProductByProductName(@PathVariable String productname) {
        return productsService.getProductByProductName(productname);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
