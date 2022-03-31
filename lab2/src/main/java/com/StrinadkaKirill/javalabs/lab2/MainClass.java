package com.StrinadkaKirill.javalabs.lab2;

import com.StrinadkaKirill.javalabs.lab2.data.Data;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {


    public static void main(String[] args) throws IOException {

        final String INPUT_FILE_NAME;
        final String OUTPUT_FILE_NAME;

        try {
            if (args.length == 2) {
                INPUT_FILE_NAME = args[0];
                OUTPUT_FILE_NAME = args[1];

            } else if (args.length == 0) {
                INPUT_FILE_NAME = null;
                OUTPUT_FILE_NAME = null;

            } else {
                throw new IllegalArgumentException("bad number of args");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }


        //here we are getting input from required reader
        ArrayList<String> listOfInput = MyExecutor.createReader(INPUT_FILE_NAME);
        if (listOfInput == null) {
            System.out.println("smth wrong with input\n");
            return;
        }


        //создается список Data из входящих данных
        ArrayList<Data> dataArrayList = Data.createDataList(listOfInput);

        MyExecutor executor = new MyExecutor(OUTPUT_FILE_NAME, dataArrayList);

        //executor.createCommandMap();
        executor.executeCalculator();
    }

}
