package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonToJavaTest extends SpartanTestBase {
@DisplayName("Get one spartan and deserialize to map")
    @Test
    public void oneSpartanToMap(){

Response response= given().
                        accept(ContentType.JSON).
                        pathParam("id",15).
                    when().
                        get("/api/spartans/{id}").
                    then().statusCode(200).
                         extract().response();

    Map<String,Object> jsonMap=response.as(Map.class);
    System.out.println(jsonMap.toString());
    String name = (String) jsonMap.get("name");
    assertThat(name,is("Meta"));

}
@DisplayName("Get all Spartans to Java data structure")
@Test
    public void getAllSpartan(){


    Response response = get("/api/spartans").then().statusCode(200).extract().response();

    List<Map<String, Object>>listOfMap= response.as(List.class);
    System.out.println(listOfMap.get(1).get("name"));

    Map<String, Object> spartan3= listOfMap.get(2);


}

}
