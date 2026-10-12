
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import com.qa.automation.models.Post;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class GetPostTest extends BaseTest {



    @Test
    public void verifyCreatePost() {

        String requestBody = """
        {
          "title": "API Automation",
          "body": "My first POST test using Rest Assured",
          "userId": 1
        }
        """;

        given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(requestBody)

                .when()
                .post("/posts")

                .then()
                .statusCode(201)
                .body("title", equalTo("API Automation"))
                .body("body",
                        equalTo("My first POST test using Rest Assured"))
                .body("userId", equalTo(1));
    }



    @Test
    public void verifyGetPost() {

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
    public void verifyGetAllPosts() {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", equalTo(1));
    }

    @Test
    public void createPostUsingPojo() {

        Post post = new Post(
                "API Automation",
                "Learning POJO with Rest Assured",
                1
        );

        System.out.println("===== REQUEST BODY =====");
        System.out.println(
                "{\n" +
                        "  \"title\": \"" + post.getTitle() + "\",\n" +
                        "  \"body\": \"" + post.getBody() + "\",\n" +
                        "  \"userId\": " + post.getUserId() + "\n" +
                        "}"
        );

        io.restassured.response.Response response =
                given()
                        .spec(requestSpec)
                        .contentType("application/json")
                        .body(post)
                        .when()
                        .post("/posts");

        System.out.println("\n===== RESPONSE =====");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println(
                io.restassured.path.json.JsonPath
                        .from(response.asString()).prettify()
        );

        response.then()
                .statusCode(201)
                .body("title", equalTo("API Automation"))
                .body("body",
                        equalTo("Learning POJO with Rest Assured"))
                .body("userId", equalTo(1));

        System.out.println("\n===== TEST RESULT =====");
        System.out.println("POST API test passed!");
    }



    @Test
    public void verifyNonExistentPost() {

        given()
                .spec(requestSpec)

                .when()
                .get("/posts/999999")

                .then()
                .statusCode(404);
    }

}
