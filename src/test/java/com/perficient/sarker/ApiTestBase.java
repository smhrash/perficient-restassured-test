package com.perficient.sarker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.log4testng.Logger;

import java.io.*;
import java.util.Properties;

public class ApiTestBase {

    private static final Logger logger = Logger.getLogger(ApiTestBase.class);

    private volatile RequestSpecification req;

    private static final String LOGS_FILE = "apiLogs.txt";

    private static Properties properties;
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/application.properties";

    private PrintStream logStream;

    private ApiTestBase() {
        initializedLogSteam();
    }

    // Singleton instance
    private static ApiTestBase instance;

    public static synchronized ApiTestBase getInstance() {
        if (instance == null) {
            instance = new ApiTestBase();
        }
        return instance;
    }

    private void initializedLogSteam() {
        try {
            logStream = new PrintStream(new FileOutputStream(LOGS_FILE, true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to Initialize log file " + LOGS_FILE, e);
        }
        logger.info("Initialized log stream successfully");
    }

    private static synchronized void loadProperties() {
        if (properties == null) {
            properties = new Properties();

            try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
                properties.load(fileInputStream);
            } catch (IOException e) {

                throw new RuntimeException("Unable to read the file " + PROPERTIES_FILE_PATH, e);
            }
        }
        logger.info("File has been read successfully");
    }

    public RequestSpecification requestSpec() {
        logger.info("Request Specification is building...");
        if (req == null) {
            synchronized (ApiTestBase.class) {
                if (req == null) {
                    loadProperties();

                    req = new RequestSpecBuilder()
                            .setBaseUri(properties.getProperty("base.uri"))
                            .addFilter(new RequestLoggingFilter(logStream))
                            .addFilter(new ResponseLoggingFilter(logStream))
                            .addFilter(new ErrorLoggingFilter(logStream))
                            .setContentType(ContentType.JSON)
                            .build();
                }

            }
        }
        logger.info("Request Specification has been built");
        return req;
    }

    public String getResponseValue(Response response, String key) {
        String resp = response.asString();
        logger.info("Retrieving value from response body...");
        return new JsonPath(resp).getString(key);
    }
}
