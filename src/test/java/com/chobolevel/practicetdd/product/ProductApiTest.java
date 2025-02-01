package com.chobolevel.practicetdd.product;

import com.chobolevel.practicetdd.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductApiTest extends ApiTest {

    @Test
    void 상품등록() {
        final var request = ProductSteps.상품등록요청_생성();

        final var response = ProductSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        final var createRequest = ProductSteps.상품등록요청_생성();
        final var createResponse = ProductSteps.상품등록요청(createRequest);
        final var productId = createResponse.body().asString();

        final var response = ProductSteps.상품조회요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productId).isEqualTo(response.jsonPath().getString("id"));
    }

}
