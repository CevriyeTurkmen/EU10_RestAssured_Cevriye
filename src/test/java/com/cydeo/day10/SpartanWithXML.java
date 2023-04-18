package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("Get request to /api/spartans and verify xml")
    @Test
    public void getSpartanXML(){

        given().accept(ContentType.XML).and().auth().basic("admin","admin").
                when().get("/api/spartans").
                then().statusCode(200).contentType("application/xml;charset=UTF-8").
                body("List.item[0].name",is("Meade")).
                body("List.item[0].gender",is("Male")).log().all();


    }

    @DisplayName("Get request /api/spartans with xml path")
    @Test
    public void xmlPath(){

       Response response= given().accept(ContentType.XML).and().auth().basic("admin","admin").
                when().get("/api/spartans");

        XmlPath xmlPath=response.xmlPath();

        String name = xmlPath.getString("List.item[2].name");
        System.out.println("name = " + name);

        List<Object> nameList  = xmlPath.getList("List.item.name");
        System.out.println(nameList);



    }

}
