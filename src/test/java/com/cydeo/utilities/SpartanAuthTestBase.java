package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("SpartanAuthIP");
    }

    @AfterAll
    public static void tearDown(){

        // DBUtils.destroy();
    }


}
