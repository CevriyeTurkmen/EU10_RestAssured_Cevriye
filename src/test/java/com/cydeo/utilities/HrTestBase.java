package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("HrIP");


    }
}
