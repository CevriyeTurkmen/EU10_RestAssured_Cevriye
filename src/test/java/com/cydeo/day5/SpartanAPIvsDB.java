package com.cydeo.day5;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanAPIvsDB extends SpartanTestBase {
@DisplayName("Get one Spartan API and Database")
@Test
    public void TestDB1(){

    String query= "select SPARTAN_ID, NAME, GENDER, PHONE from SPARTANS where SPARTAN_ID=15";

    Map<String,Object> dbMap= DBUtils.getRowMap(query);
    System.out.println(dbMap);


    Response response =given().accept(ContentType.JSON).and().pathParam("id",15).
            and().get("/api/spartans/{id}").
    then().statusCode(200).contentType("application/json").
            extract().response();

    Map<String,Object> apiTest= response.as(Map.class);
    System.out.println(apiTest);

    assertThat(apiTest.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
    assertThat(apiTest.get("name"),is(dbMap.get("NAME")));
    assertThat(apiTest.get("gender"),is(dbMap.get("GENDER")));
    assertThat(apiTest.get("phone").toString(),is(dbMap.get("PHONE").toString()));







}


}



