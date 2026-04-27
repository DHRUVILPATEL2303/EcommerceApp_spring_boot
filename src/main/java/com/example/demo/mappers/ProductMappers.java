package com.example.demo.mappers;

import com.example.demo.dto.ProductWIthCategoryDTO;
import com.example.demo.dto.ProductsDTO;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repository.CategoryRepository;

public class ProductMappers {

    public static ProductsDTO toDTO(Product product) {
        return ProductsDTO.builder()
                .id(product.getId().longValue())
                .price(product.getPrice())
                .title(product.getTitle())
                .image(product.getImage())
                .discount(product.getDiscount())
                .color(product.getColor())
                .brand(product.getBrand())
                .description(product.getDescription())
                .popular(product.isPopular())
                .model(product.getModel())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public static Product toEntity(ProductsDTO dto, CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));

        return Product.builder()
                .title(dto.getTitle())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .color(dto.getColor())
                .price(dto.getPrice())
                .discount(dto.getDiscount())
                .image(dto.getImage())
                .description(dto.getDescription())
                .popular(dto.isPopular())
                .category(category)
                .build();
    }

    public static ProductWIthCategoryDTO toProductWithCategoryDTO(Product product) {
        return ProductWIthCategoryDTO.builder()
                .id(product.getId())
                .image(product.getImage())
                .color(product.getColor())
                .price(product.getPrice())
                .description(product.getDescription())
                .discount(product.getDiscount())
                .model(product.getModel())
                .title(product.getTitle())
                .brand(product.getBrand())
                .popular(product.isPopular())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .build();
    }
}
