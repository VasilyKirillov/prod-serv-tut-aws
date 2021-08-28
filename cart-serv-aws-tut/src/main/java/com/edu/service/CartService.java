package com.edu.service;

import com.edu.entity.ProductEntity;
import com.edu.model.Cart;

public interface CartService {
    Cart getCartByID(long id);
    void deleteCartByID(long id);
    void putProduct(long id, ProductEntity product);
    void checkout(long id);
}
