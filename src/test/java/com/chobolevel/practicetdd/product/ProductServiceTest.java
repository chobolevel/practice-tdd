package com.chobolevel.practicetdd.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    private static CreateProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new CreateProductRequest(name, price, discountPolicy);
    }

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepository();
        this.productPort = new ProductAdapter(productRepository);
        this.productService = new ProductService(productPort);
    }

    @Test
    void 상품등록() {
        final CreateProductRequest request = 상품등록요청_생성();
        productService.create(request);
    }

}
