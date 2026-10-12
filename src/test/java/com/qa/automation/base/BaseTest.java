
package com.qa.automation.base;

import com.qa.automation.specs.RequestSpecFactory;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;


// Provides common test setup and initializes the request specification before each test.
public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeMethod
    public void setUp() {
        requestSpec = RequestSpecFactory.create();
    }
}
