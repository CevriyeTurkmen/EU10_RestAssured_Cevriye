package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://107.23.106.127:8000";

    }

    @Test
    public void test1(){

          /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Meta" should be in response payload
       */

        Response response= given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Meta"));

    }

       /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 500).when().
                get("/api/spartans/{id}");

        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));


    }

@Test
    public void test3(){


    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    Response response= given().accept(ContentType.JSON).
            and().queryParam("nameContains","e").
            and().queryParam("gender","Female")
    .when().get("/api/spartans/search");
    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));


}

@Test
    public void test4(){

    Map<String,Object> queryMap= new HashMap<>();
    queryMap.put("nameContains","e");
    queryMap.put("gender","Female");

    Response response=given().accept(ContentType.JSON).log().all().
            and().queryParams(queryMap).
            when().get("/api/spartans/search");

    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));


    }



}
