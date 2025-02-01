package com.chobolevel.practicetdd.product;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
class ProductService {

    private final ProductPort productPort;

    public ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody final CreateProductRequest request) {
        Product product = new Product(UUID.randomUUID().toString(),
                request.name(),
                request.price(),
                request.discountPolicy());
        var result = productPort.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ProductResponse getProduct(final String id) {
        Product product = productPort.getProduct(id);
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy());
    }
}
