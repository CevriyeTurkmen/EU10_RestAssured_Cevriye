package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://107.23.106.127:8000";

    }

    @Test
    public void test1() {

        /*
        Given accept type application/xml
        When user send GET request to api/spartans/12 end point
        Then status code must be 406
        And responce content type must be application/xml;charset=UTF-8
         */

        Response response = given().accept(ContentType.XML).when().get("api/spartans/12");
        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }


}
