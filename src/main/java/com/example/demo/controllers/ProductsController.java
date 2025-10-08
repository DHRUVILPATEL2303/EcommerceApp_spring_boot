package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.services.ICategoryService;
import com.example.demo.services.IProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("products")
public class ProductsController {

    private final IProductsService productsService;

    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public List<ProductsDTO> getAllProducts() throws IOException {
        System.out.println("Data fetched for all products");
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductsDTO getSpecificProduct(@PathVariable int id) throws Exception {
        System.out.println("Data fetched for product id -> " +id);

        return productsService.getSpecificProduct(id);
    }

    @PostMapping
    public FakeStoreCreateProductResponseDTO createProduct(@RequestBody AddProductDTO addProductDTO) throws IOException{
        System.out.println("Started Product Creation");
        FakeStoreCreateProductResponseDTO response=  productsService.createProduct(addProductDTO);
        return response ;
    }


    @PutMapping("/{id}")
    public FakeStoreUpdateProductResponseDTO updateProductResponseDTO(@PathVariable int id,@RequestBody UpdateProductDTO updateProductDTO) throws IOException{
        System.out.println("Update Product Started");
        FakeStoreUpdateProductResponseDTO response=productsService.updateProduct(id,updateProductDTO);
        return response;
    }

    @DeleteMapping("/{id}")
    public FakeStoreDeleteProductResponseDTO deleteProductResponseDTO(@PathVariable int id)throws IOException{
        System.out.println("Started Delete Product");
        FakeStoreDeleteProductResponseDTO response=productsService.deleteProduct(id);
        return response;
    }

    @PostMapping("/db/create")
    public ResponseEntity<?> createProductInDB(@RequestBody ProductsDTO productsDTO){
        try {
            ProductsDTO product=productsService.createProductInDB(productsDTO);
            return ResponseEntity.ok(product);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Created");

        }
    }

    @GetMapping("/db")
    public ResponseEntity<?> getProductsFromDB()  {
        try {
            List<ProductsDTO> productsDTOS=productsService.getAllProducts();
            return ResponseEntity.ok(productsDTOS);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Able to Fetch Product");
        }

    }

    @GetMapping("/db/{id}")
    public ProductsDTO getProductByIdFromDB(@PathVariable("id") Integer Id) throws Exception {
        return productsService.getSpecificProductByIDFromDB(Id.longValue());
    }

    @GetMapping("/db/product/categories/{id}")
    public ProductWIthCategoryDTO productWIthCategoryDTO(@PathVariable("id") Long id) throws Exception {
        return productsService.productWithCategory(id);
    }

    @GetMapping("/db/product/{minprice}")
    public List<ProductsDTO> getExpensiveProducts(@PathVariable("minprice") Long minPrice){
        return productsService.getExpensiveProducts(minPrice);
    }

    @GetMapping("/db/product/productname/{productname}")
    public List<ProductsDTO> getProductByProductName(@PathVariable("productname") String productname){
        return productsService.getProductByProductName(productname);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

    }

//    @ExceptionHandler(ProductDeleteException.class)
//        @ExceptionHandler(ProductCreateException.class)
//we can do multiple exception handler and also they identitfy it self by given and used class name and according to it the methos is called when exception happned

}
