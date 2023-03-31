package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://107.23.106.127:1000/ords/hr";


    }


    @Test
    public void test1() {
        Response response = RestAssured.get("/regions");
        System.out.println(response.statusCode());

        //Given accept type is json
        //When user sends request to /regions/2
        //Then response status code must be 200
        //And body content type  is json format
        //And respoonse body contains Americas


    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).when().get("/regions/2");
        assertEquals("application/json", response.contentType());
        assertEquals(200, response.statusCode());
        assertTrue(response.body().asString().contains("Americas"));
        response.prettyPrint();

    }


}
