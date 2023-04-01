package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanTestWithPAth {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://107.23.106.127:8000";

    }


    @Test
    public void test1() {

            /*
 Given accept type is json
 And path param id is 13
 When user sends a get request to "api/spartans/{id}"
 Then status code is 200
 And content-type is "application/json"
 And response payload values match the following:
        "id": 13,
        "name": "Jaimie",
        "gender": "Female",
        "phone": 7842554879
*/

        Response response = given().accept(ContentType.JSON).and().
                pathParam("id", "10").when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        //path is returning T, and you dont need casting for it to saving to the variable.
        //path is like automatically adjust type on the left side.
        //int değere path T olduğu için string eşitleyebilirsin vs gibi.

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert the values
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);


    }


    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]");    //json array lerden oluştuğu için, ilk id bulmak için 0 index birinci id veriyor.
        System.out.println("firstId = " + firstId);

        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        //save names inside the list of string   (name içeren array elde ederiz.)
        List<String> names = response.path("name");
        System.out.println(names);

        //print each name one by one
        for (String n : names) {
            System.out.println(n);
        }
    }
}