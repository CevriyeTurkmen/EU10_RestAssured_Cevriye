package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("Get request to verify one spartan against to schema")
    @Test
    public void schemaValidation() {

        given().accept(ContentType.JSON).and().pathParam("id", 12).
                auth().basic("admin", "admin").
                when().get("/api/spartans/{id}").
                then().statusCode(200).
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")).
                log().all();
    }

@Test
    public void allSpartansSchema(){

        given().accept(ContentType.JSON).auth().basic("admin","admin").
                when().get("/api/spartans").then().statusCode(200).
                body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));
}




}
