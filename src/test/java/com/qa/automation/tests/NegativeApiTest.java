package com.qa.automation.tests;



import com.qa.automation.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeApiTest extends BaseTest {

    @Test
    public void verifyNonExistentPostReturns404() {

        System.out.println("\n===== NEGATIVE: UNKNOWN POST =====");
        System.out.println("INPUT METHOD: GET");
        System.out.println("INPUT ENDPOINT: /posts/999999");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/999999");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());
        response.prettyPrint();

        response.then()
                .statusCode(404);
    }

    @Test
    public void verifyInvalidEndpointReturns404() {

        System.out.println("\n===== NEGATIVE: INVALID ENDPOINT =====");
        System.out.println("INPUT METHOD: GET");
        System.out.println("INPUT ENDPOINT: /invalid-endpoint");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/invalid-endpoint");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());

        response.then()
                .statusCode(404);
    }

    @Test
    public void verifyUnknownUserFilterReturnsEmptyList() {

        System.out.println("\n===== NEGATIVE: UNKNOWN USER =====");
        System.out.println("INPUT METHOD: GET");
        System.out.println(
                "INPUT ENDPOINT: /posts?userId=999999");

        Response response = given()
                .spec(requestSpec)
                .queryParam("userId", 999999)
                .when()
                .get("/posts");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());
        System.out.println("OUTPUT BODY:");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    public void verifyGetResponseContainsJson() {

        System.out.println("\n===== VALIDATION: JSON RESPONSE =====");
        System.out.println("INPUT METHOD: GET");
        System.out.println("INPUT ENDPOINT: /posts/1");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());
        System.out.println("OUTPUT CONTENT TYPE: "
                + response.getContentType());

        response.then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }
}
