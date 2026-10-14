
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdatePostTest extends BaseTest {

    // Scenario 1: PUT - Update the complete post
    @Test
    public void updateCompletePostUsingPut() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 1);
        payload.put("title", "Updated Automation Title");
        payload.put("body", "Updated body using PUT");
        payload.put("userId", 1);

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Automation Title"))
                .body("body", equalTo("Updated body using PUT"))
                .body("userId", equalTo(1));
    }

    // Scenario 2: PUT - Verify updated fields are returned
    @Test
    public void verifyPutResponseFields() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 2);
        payload.put("title", "Second Updated Post");
        payload.put("body", "Validating PUT response");
        payload.put("userId", 2);

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/posts/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("title", equalTo("Second Updated Post"))
                .body("body", notNullValue());
    }

    // Scenario 3: PATCH - Update only the title
    @Test
    public void updateTitleUsingPatch() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Patched Title");

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Patched Title"));
    }

    // Scenario 4: PATCH - Update multiple selected fields
    @Test
    public void updateTitleAndBodyUsingPatch() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Updated Selected Fields");
        payload.put("body", "Only selected fields sent in PATCH");

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/posts/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("title", equalTo("Updated Selected Fields"))
                .body("body", equalTo("Only selected fields sent in PATCH"));
    }
}
