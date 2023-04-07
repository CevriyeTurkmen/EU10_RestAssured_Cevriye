package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {
    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("SpartanIP");

        String dbUrl = "jdbc:oracle:thin:@107.23.106.127:1521:XE";  //change IP address: "jdbc:oracle:thin:@YOURIPCOMESHERE:1521:XE"
        String dbUsername = "SP";
        String dbPassword = "SP";

      // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

    }

    @AfterAll
    public static void tearDown(){

       // DBUtils.destroy();
    }

}
