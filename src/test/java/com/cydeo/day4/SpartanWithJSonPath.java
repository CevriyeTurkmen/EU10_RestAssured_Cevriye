package com.cydeo.day4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanWithJSonPath extends SpartanTestBase {

   @DisplayName("Get one spartan with Json Path")
@Test
    public void test1(){

       Response response = given().accept(ContentType.JSON).and().
               pathParam("id", "13").when().get("api/spartans/{id}");

       assertEquals(200, response.statusCode());
       assertEquals("application/json", response.contentType());

       System.out.println(response.path("name").toString());

       JsonPath jsonPath=response.jsonPath();

       int id= jsonPath.getInt("id");
       String name=jsonPath.getString("name");
       String gender=jsonPath.getString("gender");
       long phone=jsonPath.getLong("phone");

       System.out.println("phone = " + phone);
       System.out.println("gender = " + gender);
       System.out.println("name = " + name);
       System.out.println("id = " + id);

}
}
