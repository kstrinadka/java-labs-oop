package com.StrinadkaKirill.javalabs.lab2;

import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myCommand.*;

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

                System.out.println("input from file\n");        //delete
            } else if (args.length == 0) {
                INPUT_FILE_NAME = null;
                OUTPUT_FILE_NAME = null;

                System.out.println("std console input\n");      //delete
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


        //просто чтобы посмотреть что считалось
        //потом удалить
        if (listOfInput != null) {
            for (String str: listOfInput) {
                System.out.println(str);
            }
        }


        //создается список Data из входящих данных
        ArrayList<Data> dataArrayList = Data.createDataList(listOfInput);



        MyExecutor executor = new MyExecutor(INPUT_FILE_NAME, OUTPUT_FILE_NAME, dataArrayList);

        System.out.println("\nhere\n");

        executor.createCommandMap();
        executor.printCommandMap();
        executor.executeCalculator();
    }

}
