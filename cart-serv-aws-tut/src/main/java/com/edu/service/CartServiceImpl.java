package com.edu.service;

import com.edu.entity.CartEntity;
import com.edu.entity.ProductEntity;
import com.edu.model.Cart;
import com.edu.model.CheckoutResponse;
import com.edu.repo.CartRepo;
import com.edu.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final RestTemplate restTemplate;

    @Value("${order-service.url}")
    private String orderUrl;

    public CartServiceImpl(CartRepo repo, ProductRepo productRepo, RestTemplate restTemplate) {
        this.cartRepo = repo;
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Cart getCartByID(long id) {
        CartEntity cartEntity = cartRepo.findById(id)
                .orElseGet(() -> cartRepo.save(new CartEntity(id)));
        System.out.println("*** getCartByID: "+id+" cart:"+cartEntity);
        return new Cart(cartEntity);
    }
    @Override
    @Transactional
    public void deleteCartByID(long id) {
        Optional<CartEntity> cartOption = cartRepo.findById(id);
        cartOption.ifPresent(cartRepo::delete);
    }
    @Override
    @Transactional
    public void putProduct(long id, ProductEntity product) {
        CartEntity cart = cartRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cart with id" + id + "not exists"));
        product.setCart(cart);
        ProductEntity savedProduct = productRepo.save(product);

        System.out.println("*** savedProduct" + savedProduct);
    }

    @Override
    public void checkout(long id) {
        CartEntity cartEntity = cartRepo.findById(id)
                .orElseGet(() -> cartRepo.save(new CartEntity(id)));
        Cart cart = new Cart(cartEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Cart> request = new HttpEntity<>(cart, headers);
        CheckoutResponse checkoutResponse = restTemplate.postForObject(orderUrl + "/order", request, CheckoutResponse.class);
        System.out.println("*** checkout response:" + checkoutResponse);
    }
}
