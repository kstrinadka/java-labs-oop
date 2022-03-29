package com.StrinadkaKirill.javalabs.lab2;


//внутри будет калькулятор, reader, фабрика

import com.StrinadkaKirill.javalabs.lab2.Reader.AbstractReader;
import com.StrinadkaKirill.javalabs.lab2.Reader.ConsoleReader;
import com.StrinadkaKirill.javalabs.lab2.Reader.FileReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MyExecutor {

    //Calculator calculator;

    //MyReader myReader;

    String inputFileName;

    ArrayList<String> stack;


    public MyExecutor(String fileName) {

        this.inputFileName = fileName;

    }

    public static ArrayList<String> createReader (String INPUT_FILE_NAME) {

        AbstractReader reader = null;
        final String readerName;
        ArrayList<String> listOfInput = null;

        if (INPUT_FILE_NAME == null) {
            readerName = ConsoleReader.class.getName();
        }
        else {
            readerName = FileReader.class.getName();
        }

        try {
            Class<?> clazz = Class.forName(readerName);
            reader = (AbstractReader) clazz.getDeclaredConstructor(String.class).newInstance(INPUT_FILE_NAME);
        }
        catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        try {
            listOfInput = reader.getText();
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("smth wrong with input");
        }

        return listOfInput;
    }

}
