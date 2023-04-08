package com.cydeo.day6;

import com.cydeo.pojo.*;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("Get one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON).and().
                pathParam("id", 15).
                when().get("/api/spartans/{id}").
                then().statusCode(200).contentType("application/json").
                extract().response();

        //De serialize --> JSON to POJO (java custom class)
        //2 different way to do this
        //1.using as() method
        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to de serialize(converting JSON to Java class)

        Spartan spartan15 = response.as(Spartan.class);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        //second way of deserialize json to java
        //2.using JsonPath to deserialize to custom class

        JsonPath jsonPath = response.jsonPath();
        Spartan s15 = jsonPath.getObject("", Spartan.class);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());


    }

    @DisplayName("Get one spartan from search endpoint and use POJO")
    @Test
    public void spartanSearchWithPojo() {

        JsonPath jsonPath = given().accept(ContentType.JSON).and().
                queryParams("nameContains", "a", "gender", "Male").
                when().get("/api/spartans/search").
                then().statusCode(200).extract().jsonPath();

        Spartan sp = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("sp.getPhone() = " + sp.getPhone());
        System.out.println("sp = " + sp);

    }

    @Test
    public void Test3(){

        Response response = given().accept(ContentType.JSON).and().
                queryParams("nameContains", "a", "gender", "Male").
                when().get("/api/spartans/search").
                then().statusCode(200).extract().response();

        Search searchResult = response.as(Search.class);
        System.out.println(searchResult.getContent().get(0).getName());
        System.out.println("searchResult = " + searchResult);


    }
@DisplayName("Get spartans/search and save as List<Spartan> ")
    @Test
    public void test4(){

List<Spartan>list=     given().
                            accept(ContentType.JSON).and().
                            queryParams("nameContains", "a", "gender", "Male").
                        when().
                            get("/api/spartans/search").
                        then().
                            statusCode(200).extract().response().jsonPath().getList("content",Spartan.class);


    System.out.println("list.get(1) = " + list.get(1));
    System.out.println("list = " + list);
    System.out.println("list.get(1).getName() = " + list.get(1).getName());
}


}
