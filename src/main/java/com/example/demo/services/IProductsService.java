package com.example.demo.services;

import com.example.demo.dto.ProductWIthCategoryDTO;
import com.example.demo.dto.ProductsDTO;

import java.util.List;

public interface IProductsService {

    List<ProductsDTO> getAllProducts();

    ProductsDTO getProductById(Long id) throws Exception;

    ProductsDTO createProduct(ProductsDTO productDTO);

    ProductsDTO updateProduct(Long id, ProductsDTO productDTO) throws Exception;

    void deleteProduct(Long id);

    ProductWIthCategoryDTO productWithCategory(Long id) throws Exception;

    List<ProductsDTO> getExpensiveProducts(Long minPrice);

    List<ProductsDTO> getProductByProductName(String productname);
}
