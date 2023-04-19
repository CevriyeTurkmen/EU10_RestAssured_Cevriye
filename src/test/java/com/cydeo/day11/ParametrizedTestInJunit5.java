package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ParametrizedTestInJunit5 {

@ParameterizedTest
    @ValueSource(ints = {1,2,3,6,9,45,32})
    public void testMultipleNumbers(int number){

    System.out.println(number);

}

@ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","Tj"})
    public void testMultipleNames(String name){

    System.out.println(name);
}

@ParameterizedTest
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035})
    public void testzipCode(int zipCode){

            given().
                    accept(ContentType.JSON).pathParam("zipcode",zipCode).
            when().get("https://api.zippopotam.us/us/{zipcode}").
            then().
                    statusCode(200);


}

}
