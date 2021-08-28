package com.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long productId;
    private String category;
    private String brand;
    private String title;
    private String description;
    private double price;
}
