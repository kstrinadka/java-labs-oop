package com.StrinadkaKirill.javalabs.lab1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



/**
 * MyWriter getting sorted ArrayList and print it to csv file
 */
public class MyWriter {

    private final int totalWords;

    private final String csvFileName;

    private final ArrayList<MyCounter.MyData> sortedList;

    private final ArrayList<String> resultForCSV;


    public MyWriter(int totalWords, ArrayList<MyCounter.MyData> sortedList, String csvFileName) {
        this.totalWords = totalWords;
        this.sortedList = sortedList;
        this.csvFileName = csvFileName;

        resultForCSV = new ArrayList<>();

        formatData();
    }

    //convert input list to list of String for output to csv
    private void formatData () {

        for (MyCounter.MyData str : sortedList) {
            resultForCSV.add(str.getString() + Constants.DIVIDER + getRelativeFrequency(str.frequency()));
        }
    }


    //return relative frequency for each word in our text
    private String getRelativeFrequency(int amount) {

        double result = ((double)amount / (double) totalWords) * Constants.ONE_HUNDRED_PERCENT;

        return String.valueOf(result);
    }


    public void printToCSV () throws IOException  {


        final String pathToFIle =
                String.format("out\\production\\lab1\\com\\StrinadkaKirill\\javalabs\\lab1\\%s", csvFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFIle));

        for (String str: resultForCSV) {
            writer.write(str + "\n");
        }

        writer.close();

    }


    public void testPrint () {

        for (String str: resultForCSV){
            System.out.println(str);
        }
    }


}
