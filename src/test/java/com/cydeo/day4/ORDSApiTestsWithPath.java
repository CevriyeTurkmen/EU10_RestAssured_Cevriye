package com.cydeo.day4;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestsWithPath extends HrTestBase {

   @DisplayName("Get request to countries with path method")
    @Test
    public void test1(){

       Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}").
               when().get("/countries");


       System.out.println("response.path(\"limit\") = " + response.path("limit"));
       String firstCountry=response.path("items[0].country_id");
       System.out.println(firstCountry);
       
       String secondCountry=response.path("items[1].country_name");
       System.out.println("secondCountry = " + secondCountry);

       String href=response.path("items[2].links[0].href");
       System.out.println("href = " + href);

       List<String> countryname= response.path("items.country_name");
       System.out.println("countryname = " + countryname);

       for (String country : countryname) {
           System.out.println("country = " + country);

       }


   }
}
