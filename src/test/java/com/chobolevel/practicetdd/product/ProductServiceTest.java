package com.chobolevel.practicetdd.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    
    @Test
    void 상품조회() {
        // 검증 -> 검증에 필요한 로직 순서로 하는 방법도 있음

        // 상품 등록
        var createResponse = productService.create(ProductSteps.상품등록요청_생성());
        var id = createResponse.getBody().toString();

        // 상품 조회
        final ProductResponse response = productService.getProduct(id);
        
        // 상폼 조회 응답 검증
        assertThat(response).isNotNull();
    }

}
