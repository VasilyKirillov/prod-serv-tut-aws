package com.edu.service;

import com.edu.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    Product getProduct(long id);

    Product putProduct(Product product);

    Collection<Product> getProducts();
}
