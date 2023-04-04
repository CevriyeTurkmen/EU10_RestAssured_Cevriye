package com.cydeo.day4;


import com.cydeo.utilities.HrTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {

        baseURI = "https://api.training.cydeo.com";

    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 6).
                when().get("https://api.training.cydeo.com/student/{id}");

        JsonPath jsonPath = response.jsonPath();

        //send a get request to student id 6 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /transfer-encoding = chunked
        //verify Date header exists
        //assert that
            /*
                firstName Mike
                batch 26
                companyName Cydeo
                state Virginia
                using JsonPath
             */

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("chunked", response.header("transfer-encoding"));
        assertTrue(response.headers().hasHeaderWithName("date"));
        String name= jsonPath.getString("students[0].firstName");
        assertEquals("Mike",name);
        int batch=jsonPath.getInt("students[0].batch");
        assertEquals(26,batch);
        String company=jsonPath.getString("students[0].company.companyName");
        assertEquals("Cydeo",company);
        String state=jsonPath.getString("students[0].company.address.state");
        assertEquals("Virginia",state);


    }

}
