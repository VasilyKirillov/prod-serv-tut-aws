package com.edu.model;

import com.edu.entity.CartEntity;
import com.edu.entity.ProductEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
public class Cart {
    private final long cartId;
    private final Set<Product> products;

    public Cart(CartEntity cartEntity) {
        this.cartId = cartEntity.getCartId();
        products = new HashSet<>();
        for (ProductEntity productEntity : cartEntity.getProducts()) {
            products.add(new Product(productEntity));
        }
    }
}
