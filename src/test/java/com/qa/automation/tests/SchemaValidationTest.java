
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator
        .matchesJsonSchemaInClasspath;

public class SchemaValidationTest extends BaseTest {

    @Test
    public void validateGetPostSchema() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/post-schema.json"));
    }

    @Test
    public void validateGetAllPostsSchema() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/posts-array-schema.json"));
    }

    @Test
    public void validatePostResponseSchema() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Schema Testing");
        payload.put("body", "Validating POST response");
        payload.put("userId", 1);

        given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/post-schema.json"));
    }

    @Test
    public void validatePutResponseSchema() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 1);
        payload.put("title", "Updated Title");
        payload.put("body", "Updated Body");
        payload.put("userId", 1);

        given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/post-schema.json"));
    }

    @Test
    public void validatePatchResponseSchema() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Patched Title");

        given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/post-schema.json"));
    }

    @Test
    public void validateDeleteResponseSchema() {

        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1");

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete-schema.json"));
    }
}
