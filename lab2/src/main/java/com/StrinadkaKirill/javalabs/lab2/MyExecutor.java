package com.StrinadkaKirill.javalabs.lab2;


//внутри будет калькулятор, reader, фабрика

import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myCommand.AbstractCommand;
import com.StrinadkaKirill.javalabs.lab2.reader.AbstractReader;
import com.StrinadkaKirill.javalabs.lab2.reader.ConsoleReader;
import com.StrinadkaKirill.javalabs.lab2.reader.FileReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.TreeMap;

public class MyExecutor {

    //Calculator calculator;

    //MyReader myReader;

    private String inputFileName;

    private String outputFileName;

    //stack for calculator
    private ArrayList<String> stack;

    //here will be variables that defining in executing
    private TreeMap<String, Double> defineVariables;

    private MyContext context;

    //here data from input
    private final ArrayList<Data> dataArrayList;

    //map with instances of commands
    private TreeMap<String, AbstractCommand> createdCommandMap;




    public MyExecutor(String inputFileName, String outputFileName, ArrayList<Data> dataArrayList) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.dataArrayList = dataArrayList;
        this.defineVariables = new TreeMap<>();
        this.stack = new ArrayList<>();
        if (inputFileName == null && outputFileName == null) {
            this.context = new MyContext(stack, defineVariables);
        }
        else if (inputFileName != null && outputFileName != null) {
            this.context = new MyContext(inputFileName, outputFileName, stack, defineVariables);
        }
        else {
            System.out.println("smth wrong with input\n");
        }
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


    //тут создается класс команды по ее имени+
    //возможно нужно сделать конструктор без аргументов, а аргументы передавать в метод
    private AbstractCommand fabricMethod(Data data) {

        AbstractCommand command = null;

        try {
            Class<?> clazz = Class.forName(data.getCommandClassName());
            command = (AbstractCommand) clazz.getDeclaredConstructor
                    (MyContext.class, ArrayList.class).newInstance(context, data.getArguments());
        }
        catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        return command;
    }


    //можно убрать этот метод и сразу исполнять программу здесь
    public void createCommandMap() {
        createdCommandMap = new TreeMap<>();
        for (Data data: dataArrayList) {
            createdCommandMap.put(data.getCommandClassName(), fabricMethod(data));
        }
    }


    public void executeCalculator () {

        //потом удалить
        System.out.println("\nstarting executing...\n");

        for (Data data: dataArrayList) {
            //createdCommandMap.put(data.getCommandClassName(), fabricMethod(data));

            AbstractCommand command = fabricMethod(data);
            try {
                command.doOperation();
            } catch (Exception e) {
                e.printStackTrace();            // поменять ошибку, мб по-другому отловить
            }
        }


        //удалить потом
        System.out.println("\nmy stack after executing:\n");

        for (String str: stack) {
            System.out.println(str);
        }

    }

    public void printCommandMap() {
        createdCommandMap.forEach((key, value) -> System.out.println(/*key + ":" + */value));
    }

}
