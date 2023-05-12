/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.hotwax.converter;

import static com.hotwax.converter.ConverterClass.convert;
import java.io.BufferedReader;
import java.io.FileReader;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 *
 * @author dhruv
 */
public class ConverterClassTest {
    @Test
    public void test1(){
        try{
            String input="/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV input 1.txt";
            String output="/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV output 1.jsonl";
            convert(input,output,",");
            
            BufferedReader bufferedReaderOutput = new BufferedReader(new FileReader("/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV output 1.jsonl"));
            BufferedReader bufferedReaderExpected = new BufferedReader(new FileReader("/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/ExpectedOutput1.jsonl"));
            
            String o="";
            String eo="";
            String row;
            while ((row = bufferedReaderOutput.readLine()) != null){
                o+=row;
            }
            while ((row = bufferedReaderExpected.readLine()) != null){
                eo+=row;
            }
            assertEquals(o,eo);
            System.out.println("--------=======----------");
            System.out.println(o);
            System.out.println(eo);
            System.out.println("--------=======----------");
            assertEquals(o,eo);
        }
        catch(Exception e){
            System.out.println("File Not Found");
        }
            
    }
    
    
    @Test
    public void test2(){
        try{
            String input="/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV input 2.txt";
            String output="/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV output 2.jsonl";
            convert(input,output,"|");
            
            BufferedReader bufferedReaderOutput = new BufferedReader(new FileReader("/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/DSV output 2.jsonl"));
            BufferedReader bufferedReaderExpected = new BufferedReader(new FileReader("/home/dhruv/NetBeansProjects/DSV_to_JSONL_converter/converter/src/main/res/ExpectedOutput1.jsonl"));
            
            String o="";
            String eo="";
            String row;
            while ((row = bufferedReaderOutput.readLine()) != null){
                o+=row;
            }
            while ((row = bufferedReaderExpected.readLine()) != null){
                eo+=row;
            }
            System.out.println("--------=======----------");
            System.out.println(o);
            System.out.println(eo);
            System.out.println("--------=======----------");
            assertEquals(o,eo);
        }
        catch(Exception e){
            System.out.println("File Not Found");
        }
            
    }
    
}
