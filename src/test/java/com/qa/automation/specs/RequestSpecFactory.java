
package com.qa.automation.specs;

import com.qa.automation.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

// Creates reusable Rest Assured request specifications with common configurations.

public class RequestSpecFactory {

    public static RequestSpecification create() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .setAccept(ContentType.JSON)
                .build();
    }
}
