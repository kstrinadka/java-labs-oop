package com.StrinadkaKirill.javalabs.lab2;


//внутри будет калькулятор, reader, фабрика

import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myCommand.AbstractCommand;
import com.StrinadkaKirill.javalabs.lab2.reader.MyAbstractReader;
import com.StrinadkaKirill.javalabs.lab2.reader.ConsoleReaderMy;
import com.StrinadkaKirill.javalabs.lab2.reader.FileReaderMy;
import com.StrinadkaKirill.javalabs.lab2.writer.ConsoleWriterMy;
import com.StrinadkaKirill.javalabs.lab2.writer.FileWriterMy;
import com.StrinadkaKirill.javalabs.lab2.writer.MyAbstractWriter;

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

        MyAbstractReader reader = null;
        final String readerName;
        ArrayList<String> listOfInput = null;

        if (INPUT_FILE_NAME == null) {
            readerName = ConsoleReaderMy.class.getName();
        }
        else {
            readerName = FileReaderMy.class.getName();
        }

        try {
            Class<?> clazz = Class.forName(readerName);
            reader = (MyAbstractReader) clazz.getDeclaredConstructor(String.class).newInstance(INPUT_FILE_NAME);
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

    public static void createWriterAndWrite(String OUTPUT_FILE_NAME, ArrayList<String> stack) {

        MyAbstractWriter writer = null;
        final String writerName;


        if (OUTPUT_FILE_NAME == null) {
            writerName = ConsoleWriterMy.class.getName();
        }
        else {
            writerName = FileWriterMy.class.getName();
        }

        try {
            Class<?> clazz = Class.forName(writerName);
            writer = (MyAbstractWriter) clazz.getDeclaredConstructor(String.class, ArrayList.class).
                    newInstance(OUTPUT_FILE_NAME, stack);
        }
        catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("smth wrong with creating result Writer");
        }

        try {
            writer.writeResult();
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("smth wrong with result output");
        }

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

        createWriterAndWrite(outputFileName, stack);

        //удалить потом
        /*System.out.println("\nmy stack after executing:\n");

        for (String str: stack) {
            System.out.println(str);
        }*/

    }

    public void printCommandMap() {
        createdCommandMap.forEach((key, value) -> System.out.println(/*key + ":" + */value));
    }

}
