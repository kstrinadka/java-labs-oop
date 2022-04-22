package com.StrinadkaKirill.javalabs.lab2;


import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myCommand.AbstractCommand;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException.CommandExecutingException;
import com.StrinadkaKirill.javalabs.lab2.reader.MyAbstractReader;
import com.StrinadkaKirill.javalabs.lab2.reader.ConsoleReaderMy;
import com.StrinadkaKirill.javalabs.lab2.reader.FileReaderMy;
import com.StrinadkaKirill.javalabs.lab2.writer.ConsoleWriterMy;
import com.StrinadkaKirill.javalabs.lab2.writer.FileWriterMy;
import com.StrinadkaKirill.javalabs.lab2.writer.MyAbstractWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Класс исполнения команд для калькулятора
 * Здесь создаются классы для чтения и записи результата
 */
public class MyExecutor {


    private final String outputFileName;

    //stack for calculator
    private final ArrayList<String> stack;

    //here will be variables that defining in executing
    private final TreeMap<String, Double> defineVariables;

    private final MyContext context;

    //here data from input
    private final ArrayList<Data> dataArrayList;

    //map with instances of commands
    private TreeMap<String, AbstractCommand> createdCommandMap;

    private static final Logger logger = LogManager.getLogger(MainClass.class.getName());




    public MyExecutor(String outputFileName, ArrayList<Data> dataArrayList) {
        this.outputFileName = outputFileName;
        this.dataArrayList = dataArrayList;
        this.defineVariables = new TreeMap<>();
        this.stack = new ArrayList<>();
        this.context = new MyContext(stack, defineVariables);
    }


    /**
     * Создание ридера
     * @param INPUT_FILE_NAME имя входящего вайла. Если null, то чтение из консоли
     * @return
     */
    public static ArrayList<String> createReader (String INPUT_FILE_NAME) {

        MyAbstractReader reader;
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
            logger.info("error in creating Reader class ", e);
            return null;
        }

        try {
            listOfInput = reader.getText();
        }
        catch (IOException | NullPointerException e) {
            logger.info("error in reading from input", e);
        }

        return listOfInput;
    }


    /**
     * @param OUTPUT_FILE_NAME . Если null, то запись результата происходит в консоль
     * @param stack
     */
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
            logger.info("smth wrong with creating Writer class ", e);
        }

        try {
            writer.writeResult();
        }
        catch (IOException | NullPointerException e) {
            logger.info("smth wrong with result output", e);
        }

    }


    /**
     * Создает необходимую для исполнения команду
     * @param data - содержит в себе название команды и аргументы для нее
     * @return
     */
    private AbstractCommand fabricMethod(Data data) {

        AbstractCommand command;

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



    /**
     * Последовательно исполняет все команды, полученные на вход
     * @return возвращает результат
     * @throws CommandExecutingException
     * @throws ArgsAmountException
     */
    public String executeCalculator () throws CommandExecutingException, ArgsAmountException {

        logger.info("starting executing calculator");

        for (Data data: dataArrayList) {

            AbstractCommand command = fabricMethod(data);

            try {
                command.doOperation();
            }
            catch (RuntimeException e) {
                logger.info("error in command ", e);
            }

        }

        String result = context.peekTop();

        logger.info("stop executing calculator and writing result");

        createWriterAndWrite(outputFileName, stack);

        return result;
    }




}
