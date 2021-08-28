package com.edu.controller;


import com.edu.entity.ProductEntity;
import com.edu.model.Cart;
import com.edu.service.CartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Cart getCartByID(@PathVariable long id) {
        Cart cart = service.getCartByID(id);
        System.out.println("*** cart" + cart);
        return cart;
    }

    @DeleteMapping("/{id}")
    public void deleteCartByID(@PathVariable long id) {
        service.deleteCartByID(id);
    }

    @PostMapping("/{id}")
    public void putProduct(@PathVariable long id, @RequestBody ProductEntity product) {
        service.putProduct(id, product);
    }

    @GetMapping("/checkout/{id}")
    public void checkout(@PathVariable long id) {
        service.checkout(id);
    }
}
