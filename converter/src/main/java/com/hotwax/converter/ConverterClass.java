/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotwax.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dhruv
 */

public class ConverterClass {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input file name");
        String inputFileName = sc.nextLine();
        System.out.println("Enter delimeter present in DSV file");
        String delimeter = sc.nextLine();
        String currDirPath = System.getProperty("user.dir");
        String input = currDirPath + "//" + inputFileName;
        String output = currDirPath + "/output1.jsonl";
        convert(input,output,delimeter);

    }
    public static void convert(String input, String output, String delimeter){
        
        String row;
        String headers[] = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output));
            System.out.println(output);
            while ((row = bufferedReader.readLine()) != null) {
                if (headers == null) {
                    headers = row.split("[" + delimeter + "]");
                } else {

                    // -----spliting logic--------
                    String data[] = new String[headers.length];
                    String currStr = "";
                    int j = 0;
                    boolean temp = false;
                    for (int i = 0; i < row.length(); i++) {
                        // System.out.println(temp);
                        if (row.charAt(i) == '"') {
                            temp = !temp;
                            continue;
                        }
                        if (!temp && row.charAt(i) == delimeter.charAt(0)) {
                            if (currStr.length() == 0) {
                                data[j++] = null;
                            } else
                                data[j++] = currStr;
                            System.out.println(currStr + "  -=-=   ");
                            currStr = "";
                            continue;
                        }
                        currStr += row.charAt(i);
                    }
                    JSONObject obj = getJSONObject(headers, data);
                    System.out.println(obj.toString());
                    bufferedWriter.write(obj.toString());
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.close();
            System.out.println(System.getProperty("user.dir"));
        } catch (Exception e) {
            System.out.println("File you entered is not found in current directory, Pelase check if you have entered the wrong name (mention the extentions as well)");
        }
    }

    private static JSONObject getJSONObject(String headers[], String[] data) {
        JSONObject json = new JSONObject();
        System.out.println("entered....." + data[0] + headers[0] + data[3] + headers[3]);
        for (int i = 0; i < headers.length; i++) {
            String key = headers[i];
            String value = data[i];

            if (value == null || value.isEmpty()) {
                continue;
            }
            System.out.println(key + "-" + value);
            if (key.equals("dateOfBirth")) {
                String regex = "(\\d{2})[-/](\\d{2})[-/](\\d{4})|(\\d{4})[-/](\\d{2})[-/](\\d{2})";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);

                if (matcher.matches()) {
                    String d = matcher.group(1) != null ? matcher.group(1) : matcher.group(6);
                    String m = matcher.group(2) != null ? matcher.group(2) : matcher.group(5);
                    String y = matcher.group(3) != null ? matcher.group(3) : matcher.group(4);
                    value = y + "-" + m + "-" + d;
                } else {
                    System.out.println("Invalid date format.");
                }

            }

            System.out.println(key + " " + value);
            json.put(key, value);
        }
        return json;
    }
}