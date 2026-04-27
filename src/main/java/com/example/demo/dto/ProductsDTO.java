package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsDTO {
    private Long id;
    private String title;
    private String brand;
    private String model;
    private String color;
    private Integer price;
    private Integer discount;
    private String image;
    private String description;
    private boolean popular;

    private Long categoryId;
}


//
//    title: "Apple Vision Pro",
//    brand: "Apple",
//    model: "Apple vision pro First Gen",
//    color: "Black",
//    category: "appliances",
//    discount: 1

