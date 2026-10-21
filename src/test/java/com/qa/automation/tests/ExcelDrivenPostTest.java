
package com.qa.automation.tests;

import com.qa.automation.base.BaseTest;
import com.qa.automation.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ExcelDrivenPostTest extends BaseTest {

    @DataProvider(name = "excelPostData")
    public Object[][] excelPostData() {
        return ExcelReader.getTestData(
                "PostTestData.xlsx", "PostData");
    }

    @Test(dataProvider = "excelPostData")
    public void verifyPostDetailsFromExcel(
            String postId,
            String expectedUserId,
            String expectedStatus) {

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(Integer.parseInt(expectedStatus))
                .body("id", equalTo(Integer.parseInt(postId)))
                .body("userId", equalTo(
                        Integer.parseInt(expectedUserId)));
    }
}
