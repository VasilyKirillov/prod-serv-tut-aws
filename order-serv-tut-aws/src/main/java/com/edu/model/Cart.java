package com.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private long cartId;
    private Set<Product> products;
}
