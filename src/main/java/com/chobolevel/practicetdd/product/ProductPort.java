package com.chobolevel.practicetdd.product;

interface ProductPort {
    String save(Product product);

    Product getProduct(String id);
}
