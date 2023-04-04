package com.cydeo.day4;

import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithJSonpath extends HrTestBase {

    @DisplayName("Get request to countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        JsonPath jsonPath=response.jsonPath();
        
        String country= (jsonPath.getString("items[1].country_name"));
        System.out.println("country = " + country);

      List<String> countryId =jsonPath.getList("items.country_id");
        System.out.println("countryId = " + countryId);

        List<String> countryWithRegionID= jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryWithRegionID = " + countryWithRegionID);
        
    }

    @Test
    public void test2(){



        Response response = given().queryParam("limit",107).
                when().get("/employees");

        JsonPath jsonPath=response.jsonPath();

        List<String> EmailwithIT= jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(EmailwithIT);

        List<String> employeeRich=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(employeeRich);

        String kingFirstName=jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);




    }

}
