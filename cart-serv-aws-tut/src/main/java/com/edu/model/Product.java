package com.edu.model;

import com.edu.entity.ProductEntity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Product {
    private long productId;
    private String category;
    private String brand;
    private String title;
    private String description;
    private double price;
    private int qty;

    public Product(ProductEntity entity) {
        this.productId = entity.getProductId();
        this.category = entity.getCategory();
        this.brand = entity.getBrand();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
    }
}
