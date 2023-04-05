package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanHemcrestTest extends SpartanTestBase {
    @DisplayName("Get request to Spartan/search and chaining together")
    @Test
    public void test1(){

List<String>names =given().accept(ContentType.JSON).
                        and().queryParams("nameContains","j","gender","female").
                when().
                        get("/api/spartans/search").
                then().
                        statusCode(200).
                        and().
                        body("totalElement",is(6)).
                        extract().response().jsonPath().getList("content.name");


         int statusCode=given().accept(ContentType.JSON).
                and().queryParams("nameContains","j","gender","female").
                when().
                get("/api/spartans/search").
                then().
                statusCode(200).
                and().
                body("totalElement",is(6)).
                extract().response().statusCode();

        System.out.println(statusCode);

    }


}
