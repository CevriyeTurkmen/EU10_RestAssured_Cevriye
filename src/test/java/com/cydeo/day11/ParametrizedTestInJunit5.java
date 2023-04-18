package com.cydeo.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParametrizedTestInJunit5 {

@ParameterizedTest
    @ValueSource(ints = {1,2,3,6,9,45,32})
    public void testMultipleNumbers(int number){

    System.out.println(number);

}

@ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","Tj"})
    public void testMultipleNames(String name){

    System.out.println(name);
}

}
