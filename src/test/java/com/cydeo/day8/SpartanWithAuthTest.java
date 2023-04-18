package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanWithAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get api/spartans as a public user (guest) expect 401")
    @Test
    public void test1() {

        given().accept(ContentType.JSON).when().
                get("/api/spartans").then().statusCode(401).log().all();

    }

    @DisplayName("Get request /api/spartans as admin user expect 200")
    @Test
    public void testAdmin() {
        given().
                auth().basic("admin","admin").
        when().
                get("/api/spartans").
        then().
                statusCode(200).log().all();

    }

@DisplayName("Delete spartans/{id} as editor user expect 403")
    @Test
    public void testEdidorDelete(){

        given().auth().basic("editor", "editor").and().accept(ContentType.JSON).
                pathParam("id",30).when().delete("/api/spartans/{id}").
                then().statusCode(403).log().all();

    }

}
