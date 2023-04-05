package com.cydeo.day5;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSHamcrestTest extends HrTestBase {

    @DisplayName("Get request to employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))   //items.job_id -> return all job ids as a list. and everyItem() -> we it is gonna loop for the every item in the list.(you can use is, greater than vs everything instead of equalTo)
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))  //contains with order
                //containsInRelativeOrder(): you need to give same order also. diana başa yazarsan fail olur mesela.
                //               : sadece bir isim de var mı diye kontrol etmek istersen de kullanabilirsin, illa tüm listi yazmana gerek yok.
                //containsInAnyOrder(): you can change the order
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //contains without order
                .body("items.first_name", equalToObject(names)); // equality of lists assertion

    }

    @Test
    public void test2(){

               JsonPath jsonPath= given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().jsonPath();

        //we want to chain and also get response object

        // Response response =  -> böyle yapabilirsin eğer .extract().response ile geri dönersen response a

        //extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));    //collection method hasSize() , oluşan Listin size ına bakmak için

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

        /*
        after writing .extract() method: it will give you option to return response, jsonpath, and whatever you wanna return!
        böylece given, when, then akışındaki request ve response bütününün tamamlanmışlığına
        ek olarak nereye döneceğini belirtebilirsin.
        with this .extract(). method,
        while I do verification, I can save inside the response object
         */

    }


}
