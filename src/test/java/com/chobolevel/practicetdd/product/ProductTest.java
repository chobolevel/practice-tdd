package com.chobolevel.practicetdd.product;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductTest {

    @Test
    void 상품_엔티티_수정() {
        var product = new Product(UUID.randomUUID().toString(), "상품명", 1000, DiscountPolicy.NONE);

        product.update("수정된 상품명", 2000, DiscountPolicy.NONE);

        assertThat(product.getName()).isEqualTo("수정된 상품명");
        assertThat(product.getPrice()).isEqualTo(2000);
        assertThat(product.getDiscountPolicy()).isEqualTo(DiscountPolicy.NONE);
    }
}
