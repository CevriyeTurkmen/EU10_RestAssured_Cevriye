package com.cydeo.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersIntro {

    @Test
    public void SimpleTest() {

        //actual 5+5
        //expected is(10)
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second taht accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5 + 5, is(greaterThan(9)));

        // you can check details in here
        // https://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {

        String text = "EU10 is learning Hamcrest";
        //checking for euqality is same as numbers
        assertThat(text,is("EU10 is learning Hamcrest"));
        assertThat(text,equalTo("EU10 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU10 is learning Hamcrest")));


        assertThat(text,startsWith("EU10"));
        assertThat(text,startsWithIgnoringCase("eU10"));
        assertThat(text,endsWithIgnoringCase("rest"));
        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARN"));
        String str=" ";
        assertThat(str,blankString());
        assertThat(str.trim(),emptyString());
    }
   @DisplayName("Hamcrest for Collection")
    @Test
    public void TestCollection(){

       List<Integer>listOfNumbers= Arrays.asList(1,2,4,66,77,45,32,3,6,9);

       //check size of the list
       assertThat(listOfNumbers,hasSize(10));
       //check if this list hasItem 77
       assertThat(listOfNumbers,hasItem(77));
       //check if this list hasItems 77,54,23
       assertThat(listOfNumbers,hasItems(77,54,23));

       //check if all numbers greater than 0
       assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }








}
