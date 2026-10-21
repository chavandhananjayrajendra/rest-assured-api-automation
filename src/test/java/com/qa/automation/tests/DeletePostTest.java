
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeletePostTest extends BaseTest {

    @Test
    public void deleteExistingPost() {

        System.out.println("\n===== DELETE: EXISTING POST =====");
        System.out.println("INPUT METHOD: DELETE");
        System.out.println("INPUT ENDPOINT: /posts/1");

        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .delete("/posts/1");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());
        System.out.println("OUTPUT BODY:");
        response.prettyPrint();

        response.then()
                .statusCode(200);
    }

    @Test
    public void deleteAnotherPost() {

        System.out.println("\n===== DELETE: ANOTHER POST =====");
        System.out.println("INPUT METHOD: DELETE");
        System.out.println("INPUT ENDPOINT: /posts/2");

        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/posts/2");

        System.out.println("OUTPUT STATUS: "
                + response.getStatusCode());
        response.prettyPrint();

        response.then()
                .statusCode(200);
    }
}
