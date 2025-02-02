package com.chobolevel.practicetdd.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductSteps {

    public static CreateProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new CreateProductRequest(name, price, discountPolicy);
    }

    public static ExtractableResponse<Response> 상품등록요청(CreateProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품조회요청(String productId) {
        return RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static UpdateProductRequest 상품수정요청_생성() {
        final String name = "수정된 상품명";
        final int price = 2000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new UpdateProductRequest(name, price, discountPolicy);
    }

}
