package com.chobolevel.practicetdd.product;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Product {

    @Id
    private String id;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

    public Product(String id, String name, int price, DiscountPolicy discountPolicy) {
        Assert.notNull(id, "아이디는 필수입니다.");
        Assert.hasText(name, "상품명은 필수입니다");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    // domain 적용 로직은 별도의 테스트가 필요
    public void update(final String name, final int price, final DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수입니다");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
