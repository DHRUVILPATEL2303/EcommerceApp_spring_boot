package com.example.demo.services;

import com.example.demo.dto.ProductWIthCategoryDTO;
import com.example.demo.dto.ProductsDTO;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.mappers.ProductMappers;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductsService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductsDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMappers::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDTO getProductById(Long id) throws Exception {
        return productRepository.findById(id)
                .map(ProductMappers::toDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + id));
    }

    @Override
    public ProductsDTO createProduct(ProductsDTO productDTO) {
        Product saved = productRepository.save(ProductMappers.toEntity(productDTO, categoryRepository));
        return ProductMappers.toDTO(saved);
    }

    @Override
    public ProductsDTO updateProduct(Long id, ProductsDTO productDTO) throws Exception {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + id));

        if (productDTO.getTitle() != null) existing.setTitle(productDTO.getTitle());
        if (productDTO.getBrand() != null) existing.setBrand(productDTO.getBrand());
        if (productDTO.getModel() != null) existing.setModel(productDTO.getModel());
        if (productDTO.getColor() != null) existing.setColor(productDTO.getColor());
        if (productDTO.getImage() != null) existing.setImage(productDTO.getImage());
        if (productDTO.getDescription() != null) existing.setDescription(productDTO.getDescription());
        if (productDTO.getPrice() != null) existing.setPrice(productDTO.getPrice());
        if (productDTO.getDiscount() != null) existing.setDiscount(productDTO.getDiscount());
        existing.setPopular(productDTO.isPopular());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDTO.getCategoryId()));
            existing.setCategory(category);
        }

        return ProductMappers.toDTO(productRepository.save(existing));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductWIthCategoryDTO productWithCategory(Long id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + id));
        return ProductMappers.toProductWithCategoryDTO(product);
    }

    @Override
    public List<ProductsDTO> getExpensiveProducts(Long minPrice) {
        return productRepository.findExpensiveProducts(minPrice).stream()
                .map(ProductMappers::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsDTO> getProductByProductName(String productname) {
        return productRepository.searchFullText(productname).stream()
                .map(ProductMappers::toDTO)
                .collect(Collectors.toList());
    }
}
