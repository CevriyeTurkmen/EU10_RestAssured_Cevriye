package com.cydeo.day8;

import com.cydeo.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookitTest {


    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("BookitIP");
    }

    String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";
    @DisplayName("Get all campuses")
    @Test
    public void testAuth1(){

        given().
                header("Authorization",accessToken).and().accept(ContentType.JSON).
        when().
                get("/api/campuses").
        then().
                statusCode(200).log().all();

    }
}
