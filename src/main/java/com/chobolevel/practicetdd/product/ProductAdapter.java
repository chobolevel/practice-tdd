package com.chobolevel.practicetdd.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ProductAdapter implements ProductPort {
    private final ProductRepository productRepository;

    ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String save(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public String update(String productId, UpdateProductRequest request) {
        final var product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        product.update(request.name(), request.price(), request.discountPolicy());
        return product.getId();
    }

    @Override
    public boolean delete(String productId) {
        final var product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        productRepository.deleteById(product.getId());
        return true;
    }
}
