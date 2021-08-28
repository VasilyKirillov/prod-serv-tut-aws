package com.edu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "CART")
public class CartEntity {

    @Id
    private long cartId;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ProductEntity> products = new HashSet<>();

    public CartEntity() {
    }

    public CartEntity(long cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "cartId=" + cartId +
                (products == null ? "" : products.stream()
                        .map(ProductEntity::getTitle)
                        .collect(Collectors.joining(","))
                )  +
                '}';
    }
}
