package com.StrinadkaKirill.javalabs.lab1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class mainClass {


    public static void main(String[] args) {


        final String INPUT_FILE_NAME;
        final String OUTPUT_FILE_NAME;

        try {
            if (args.length == 2) {
                INPUT_FILE_NAME = args[0];
                OUTPUT_FILE_NAME = args[1];
            }
            else {
                throw new IllegalArgumentException("bad number of args");
            }
        }
        catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }



        ArrayList<String> list;

        try {
            list = MyReader.openAndReadFromFile(INPUT_FILE_NAME);
        }
        catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
            return;
        }


        MyCounter counter = new MyCounter(list);

        counter.printList();


        MyWriter writer = new MyWriter(counter.getTotalWords(), counter.getWordsWithFrequencyList(),
                OUTPUT_FILE_NAME);

        writer.testPrint();

        try {
            writer.printToCSV();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }



    }


}
