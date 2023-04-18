package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanPostRequestDemo extends SpartanTestBase {


    /*
Given accept type and Content type is JSON
And request json body is:
{
  "gender":"Male",
  "name":"Severus",
  "phone":8877445596
}
When user sends POST request to '/api/spartans'
Then status code 201
And content type should be application/json
And json payload/response/body should contain:
"A Spartan is Born!" message
and same data what is posted
*/
    @Test
    public void postMethod1() {

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

    }

    @DisplayName("Post with Map to Json")
    @Test
    public void postMethod2() {


        Map<String,Object>requestJsonBody= new LinkedHashMap<>();
        requestJsonBody.put("name", "Severus");
        requestJsonBody.put("gender","Male");
        requestJsonBody.put("phone",8877445596L);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

    }

    @DisplayName("Post with Pojo to Json")
    @Test
    public void postMethod3() {

        Spartan spartan=new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);


        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

    }

    @DisplayName("Post with Map to Spartan Class")
    @Test
    public void postMethod4() {

        Spartan spartan=new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        String expectedResponseMessage = "A Spartan is Born!";
    int idFromPost=given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan)
                .when()
                .post("/api/spartans").then().statusCode(201).body("success", is(expectedResponseMessage)).extract().jsonPath().getInt("data.id");

        //verify status code

        Spartan spartanPosted = given().accept(ContentType.JSON).and().pathParam("id", idFromPost).
                when().get("/api/spartans/{id}").
                then().statusCode(200).contentType("application/json").extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));


    }




}
