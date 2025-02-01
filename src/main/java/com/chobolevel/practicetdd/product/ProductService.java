package com.chobolevel.practicetdd.product;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
class ProductService {

    private final ProductPort productPort;

    public ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void create(final CreateProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }
}
