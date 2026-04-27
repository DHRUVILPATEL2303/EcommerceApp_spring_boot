package com.example.demo.services;

import com.example.demo.dto.AllProductsOfCategoryDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductsDTO;
import com.example.demo.entities.Category;
import com.example.demo.mappers.CategoryMapper;
import com.example.demo.mappers.ProductMappers;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        List<CategoryDTO> dtos= categoryRepository.findAll().stream().map(CategoryMapper::toDTO).toList();
        return dtos;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=CategoryMapper.toEntity(categoryDTO);

        Category saved=categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    @Override
    public CategoryDTO getSpecificCategoryByIdFromDB(Long id) throws Exception {
        return categoryRepository.findById(id).map(CategoryMapper::toDTO).orElseThrow(
                () -> new Exception("CAtegory Not found")
        );
    }

    @Override
    public List<ProductsDTO> getAllProductsByCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        return category.getProducts().stream()
                .map(ProductMappers::toDTO)
                .toList();
    }

    @Override
    public AllProductsOfCategoryDTO allProductsOfCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found with id: " + id));

        List<ProductsDTO> productDTOs = category.getProducts()
                .stream()
                .map(product -> ProductMappers.toDTO(product))
                .collect(Collectors.toList());

        return AllProductsOfCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .productsDTOS(productDTOs)
                .build();
    }
}
