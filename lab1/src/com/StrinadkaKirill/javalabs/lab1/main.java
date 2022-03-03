package com.StrinadkaKirill.javalabs.lab1;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;

public class main {


    public static void main(String[] args) {


        final String FILE_NAME;

        try {
            if (args.length == 1) {
                FILE_NAME = args[0];
            }
            else {
                throw new IllegalArgumentException("bad number of args");
            }
        }
        catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }


        ArrayList<String> list = new ArrayList<>();

        try {
            MyReader.openAndReadFromFile(FILE_NAME, list);
        }
        catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
            return;
        }

        System.out.println(list.toString());





    }


}
