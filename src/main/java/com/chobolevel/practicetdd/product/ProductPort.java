package com.chobolevel.practicetdd.product;

import java.util.List;

interface ProductPort {
    String save(Product product);

    Product getProduct(String id);

    List<Product> getProducts();
}
