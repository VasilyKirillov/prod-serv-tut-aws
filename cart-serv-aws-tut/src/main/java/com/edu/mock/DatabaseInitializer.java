package com.edu.mock;

import com.edu.entity.ProductEntity;
import com.edu.model.Cart;
import com.edu.service.CartService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseInitializer implements CommandLineRunner {

    private final CartService service;

    public DatabaseInitializer(CartService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        Cart cart10 = service.getCartByID(10);
        service.putProduct(10, new ProductEntity(
                "Drinks", "Coca-cola", "Cola light",
                "Phosphoric acid water solution (good for rust removal)", 120
        ));
        service.putProduct(10, new ProductEntity(
                "Drinks", "NeMoloko", "Oat milk",
                "Healthy and testy yummy", 82
        ));
        service.putProduct(10, new ProductEntity(
                "Food", "Sourdough bread",
                "Dark rye bread", "Everyday meal", 50
        ));

        Cart cart20 = service.getCartByID(20);
        service.putProduct(20, new ProductEntity(
                "Drinks", "NeMoloko",  "Cashew milk",
                "Healthy and testy yummy", 84
        ));
        service.putProduct(20, new ProductEntity(
                "Food", "Sourdough bread",
                "Whole grained wheat bread", "Everyday meal", 55
        ));
        System.out.println(cart10);
        System.out.println(cart20);
    }
}
