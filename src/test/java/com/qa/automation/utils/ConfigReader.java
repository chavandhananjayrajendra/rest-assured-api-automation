
package com.qa.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


//Properties lets Java read key-value configuration from a file. The class loads the file once and exposes the base URL through a reusable method.

public class ConfigReader {

    private static final Properties PROPERTIES = loadProperties();

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "config.properties not found in test resources"
                );
            }

            properties.load(input);
            return properties;

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load configuration", e
            );
        }
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }
}
