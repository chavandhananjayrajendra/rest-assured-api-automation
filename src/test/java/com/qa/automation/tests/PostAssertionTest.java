
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends BaseTest {

    @Test
    public void verifyPostIdAndUserId() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }

    @Test
    public void verifyTitleAndBodyAreNotEmpty() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("title", not(emptyOrNullString()))
                .body("body", not(emptyOrNullString()));
    }

    @Test
    public void verifyResponseContentType() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }

    @Test
    public void verifyAllPostsCount() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(100));
    }

    @Test
    public void verifyEveryPostHasId() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("id", everyItem(notNullValue()));
    }

    @Test
    public void verifyPostsForUserOne() {

        given()
                .spec(requestSpec)
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(1)));
    }

    @Test
    public void verifyResponseTime() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void verifyNonExistentPostStatus() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/999999")
                .then()
                .statusCode(404);
    }
}
