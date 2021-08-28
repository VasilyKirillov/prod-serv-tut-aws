package com.edu.service;

import com.edu.entity.BrandEntity;
import com.edu.entity.CategoryEntity;
import com.edu.entity.ProductEntity;
import com.edu.model.Product;
import com.edu.repo.ProductRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;
    private Map<Long, Product> cache = new HashMap<>();

    public ProductServiceImpl(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public Product getProduct(long id) {
        return cache.get(id);
//        final Optional<ProductEntity> entityOptional = repo.findById(id);
//        final ProductEntity entity = entityOptional.orElseThrow(() -> new EntityNotFoundException("product id=" + id + "not found"));
//        return convertToModel(entity);
    }

    private Product convertToModel(ProductEntity entity) {
        final Product product = new Product();
        product.setId(entity.getId());
        product.setBrandId(entity.getBrand().getId());
        product.setCategoryId(entity.getCategory().getId());
        product.setTitle(entity.getTitle());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        return product;
    }

    @Override
    public Product putProduct(Product product) {
        if (product.getId() == null) {
            product.setId(cache.size() + 1L);
        }
        cache.put(product.getId(), product);
//        final ProductEntity saved = repo.save(convertToEntity(product));
//        product.setId(saved.getId());
        return product;
    }

//    private ProductEntity convertToEntity(Product product) {
//        final ProductEntity entity = new ProductEntity();
//        final BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setId(product.getBrandId());
//        entity.setBrand(brandEntity);
//        final CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(product.getCategoryId());
//        entity.setCategory(categoryEntity);
//        entity.setTitle(product.getTitle());
//        entity.setDescription(product.getDescription());
//        entity.setPrice(product.getPrice());
//        entity.setId(product.getId());
//        return entity;
//    }

    @Override
    public Collection<Product> getProducts() {
        return cache.values();
//        return repo.findAll()
//                .stream().map(this::convertToModel)
//                .collect(Collectors.toList());
    }
}
