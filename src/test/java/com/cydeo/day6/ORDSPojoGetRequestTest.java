package com.cydeo.day6;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cydeo.pojo.*;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ORDSPojoGetRequestTest extends HrTestBase {

    @Test
    public void regionTest(){

        //create pojo classses for this response
        //then test it sending get request to regions end point
        //only pointing first region and converting your pojos

    JsonPath jsonPath = given().
                            accept(ContentType.JSON).get("/regions").
                        then().statusCode(200).extract().response().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getRId() = " + region1.getRId());


    }
    @DisplayName("Get request to employees and only get couple of values as a pojo class")
    @Test
    public void employeeTest(){

        Employee employee1 = get("/employees").then().statusCode(200).extract().jsonPath().
                getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);

        System.out.println("employee1.getFirstName() = " + employee1.getFirstName());
        System.out.println("employee1.getLastName() = " + employee1.getLastName());
        System.out.println("employee1.getJobID() = " + employee1.getJobID());
        System.out.println("employee1.getSalary() = " + employee1.getSalary());


    }
@DisplayName("Get request to region only some fields test")
    @Test
    public void regionsTest(){

    Regions regions = get("/regions").then().statusCode(200).extract().as(Regions.class);


    //verify count is 4
    assertThat(regions.getCount(),is(4));
    //create empty list to store values
    List<String> regionNames = new ArrayList<>();
    List<Integer> regionIds = new ArrayList<>();

    //get list of regions out of regions object
    List<Region> items = regions.getItems();

    //loop through each of the region, save their ids and names to empty list that we prepare
    for (Region region : items) {
        regionIds.add(region.getRId());
        regionNames.add(region.getRegion_name());
    }
    System.out.println("regionIds = " + regionIds);
    System.out.println("regionNames = " + regionNames);
    //prepare expected result
    List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
    List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

    //compare two result
    assertThat(regionIds,is(expectedRegionIds));
    assertThat(regionNames,is(equalTo(expectedRegionNames)));


}





}
