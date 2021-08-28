package com.edu.service;

import com.edu.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(long id);

    Product putProduct(Product product);

    List<Product> getProducts();
}
