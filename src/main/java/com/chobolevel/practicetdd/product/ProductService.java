package com.chobolevel.practicetdd.product;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        final Product product = new Product(UUID.randomUUID().toString(),
                request.name(),
                request.price(),
                request.discountPolicy());
        var result = productPort.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        final var result = productPort.getProducts();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable final String productId) {
        final Product product = productPort.getProduct(productId);
        final ProductResponse result = new ProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{productId}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable final String productId, @RequestBody final UpdateProductRequest request) {
        final Product product = productPort.getProduct(productId);
        product.update(request.name(), request.price(), request.discountPolicy());
        return ResponseEntity.ok().body(product.getId());
    }
}
