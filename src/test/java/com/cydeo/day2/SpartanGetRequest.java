package com.cydeo.day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {
    String baseUrl = "http://107.23.106.127:8000";

//    Given Accept type application/json
//    When user send get request to aplication/spartans end point
//    Then status code must 200
//    And response content type must be aplication/json
//    And response body should include spartan result


    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl+ "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json");







    }

//    Given Accept type application/json
//    When user send get request to api/spartans/12 end point
//    Then status code must 200
//    And response content type must be aplication/json
//    And response body should contain Sol
    @Test
    public void test2(){

        Response response = RestAssured.given().when().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/12");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json");
        Assertions.assertTrue(response.body().asString().contains("Sol"));

    }

}
