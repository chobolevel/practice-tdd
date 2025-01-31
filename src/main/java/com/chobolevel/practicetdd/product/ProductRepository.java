package com.chobolevel.practicetdd.product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class ProductRepository {
    private final Map<String, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assignId(UUID.randomUUID().toString());
        persistence.put(product.getId(), product);
    }
}
