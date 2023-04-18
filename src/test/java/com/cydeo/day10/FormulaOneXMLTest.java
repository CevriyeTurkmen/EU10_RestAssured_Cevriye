package com.cydeo.day10;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FormulaOneXMLTest {

    @BeforeAll
    public static void setUp(){

        baseURI="http://ergast.com";
        basePath="/api/f1";
    }

    @Test
    public void test1(){

        Response response = given().pathParam("driver", "alonso").
                when().
                get("/drivers/{driver}").
                then()
                .statusCode(200).extract().response();

        XmlPath xmlPath=response.xmlPath();
        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println(givenName);
        String familyName=xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println(familyName);
        String driverID= xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println(driverID);
        String url= xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println(url);

    }
}
