package com.cydeo.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class MethodSourceParametrizedTest {



    @ParameterizedTest
    @MethodSource("getNames")

    public void testPrintNames(String name){

        System.out.println(name);
    }

    public static List<String>getNames(){

        List<String>namesList= Arrays.asList("Cevriye","Suheda","Gulsah","Yasemin","Sultan","Seyma");



return namesList;
    }
}
