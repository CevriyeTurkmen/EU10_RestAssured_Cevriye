package com.cydeo.day5;

import com.cydeo.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
    @DisplayName("One Spartan with Hamcrest and chaining")
    @Test
    public void test1() {

       /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

        given().
                accept(ContentType.JSON).and().pathParam("id", 15).

                when().get(ConfigurationReader.getProperty("SpartanIP") + "/api/spartans/{id}").
                then().statusCode(200).contentType("application/json").
                body("name",
                        equalTo("Meta"), "gender", is("Female"),
                        "phone", is(1938695106));

    }

    @DisplayName("CBTraining teacher request with chaining and matchers")
    @Test
    public void teacherData() {

        given().accept(ContentType.JSON).and()
                .pathParam("id", 3)
                .when().get("https://api.training.cydeo.com/teacher/{id}")
                .then().contentType("application/json;charset=UTF-8")
                .and().statusCode(200)
                .and().headers("date", is(notNullValue())).
                body("teachers[0].firstName", is("Tet"), "teachers[0].lastName", is("DS"));

    }
    @DisplayName("Get request the teacher all and chaining")
    @Test
    public void teachersTest(){

        //verify Valter,Mario,Porter inside the all teachers

        given().accept(ContentType.JSON)
                .when().get("https://api.training.cydeo.com/teacher/all")
                .then().body("teachers.firstName",hasItems("Valter","Mario","Porter"));


    }
}
