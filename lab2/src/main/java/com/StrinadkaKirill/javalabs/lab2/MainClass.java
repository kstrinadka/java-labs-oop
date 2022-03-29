package com.StrinadkaKirill.javalabs.lab2;

import com.StrinadkaKirill.javalabs.lab2.Reader.AbstractReader;
import com.StrinadkaKirill.javalabs.lab2.Reader.ConsoleReader;
import com.StrinadkaKirill.javalabs.lab2.Reader.FileReader;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainClass {


    public static void main(String[] args) throws IOException {

        final String INPUT_FILE_NAME;
        
        try {
            if (args.length == 1) {
                INPUT_FILE_NAME = args[0];

                System.out.println("input from file\n");        //delete
            } else if (args.length == 0) {
                INPUT_FILE_NAME = null;

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


        if (listOfInput != null) {
            for (String str: listOfInput) {
                System.out.println(str);
            }
        }




    }

}
