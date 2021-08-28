package com.edu.service;

import com.edu.entity.CartEntity;
import com.edu.entity.ProductEntity;
import com.edu.model.Cart;
import com.edu.repo.CartRepo;
import com.edu.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    public CartServiceImpl(CartRepo repo, ProductRepo productRepo) {
        this.cartRepo = repo;
        this.productRepo = productRepo;
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
}
