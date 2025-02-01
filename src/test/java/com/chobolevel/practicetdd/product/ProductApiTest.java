package com.chobolevel.practicetdd.product;

import com.chobolevel.practicetdd.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Test
    void 상품등록() {
        // given
        final var request = ProductSteps.상품등록요청_생성();

        // when
        final var response = ProductSteps.상품등록요청(request);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        //given
        final var createRequest = ProductSteps.상품등록요청_생성();
        final var createResponse = ProductSteps.상품등록요청(createRequest);
        final var productId = createResponse.body().asString();

        // when
        final var response = ProductSteps.상품조회요청(productId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productId).isEqualTo(response.jsonPath().getString("id"));
    }

}
