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
    void 상품목록조회() {
        // given

        // when
        final var response = ProductSteps.상품목록조회요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
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

    @Test
    void 상품수정() {
        // given
        final var createRequest = ProductSteps.상품등록요청_생성();
        final var createResponse = ProductSteps.상품등록요청(createRequest);

        final var productId = createResponse.body().asString();

        final var createJsonProduct = ProductSteps.상품조회요청(productId).jsonPath();

        // when
        final var updateRequest = ProductSteps.상품수정요청_생성();
        final var updateResponse = ProductSteps.상품수정요청(productId, updateRequest);

        final var updateJsonProduct = ProductSteps.상품조회요청(productId).jsonPath();

        // then
        assertThat(updateJsonProduct.getString("id")).isEqualTo(createJsonProduct.getString("id"));
        assertThat(updateJsonProduct.getString("name")).isEqualTo(updateRequest.name());
        assertThat(updateJsonProduct.getInt("price")).isEqualTo(updateRequest.price());
        assertThat(updateJsonProduct.getString("discountPolicy")).isEqualTo(updateRequest.discountPolicy().name());
    }

    @Test
    void 상품삭제() {
        // given
        final var createRequest = ProductSteps.상품등록요청_생성();
        final var createResponse = ProductSteps.상품등록요청(createRequest);
        final var productId = createResponse.body().asString();

        // when
        final var deleteResponse = ProductSteps.상품삭제요청(productId);
        final var getDeletedProductResponse = ProductSteps.상품조회요청(productId);

        // then
        assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(getDeletedProductResponse.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
