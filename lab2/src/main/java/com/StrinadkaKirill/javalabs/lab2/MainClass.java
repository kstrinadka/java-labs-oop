package com.StrinadkaKirill.javalabs.lab2;

import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.ArrayList;

import static com.StrinadkaKirill.javalabs.lab2.Constants.*;

public class MainClass {

    private static final Logger logger = LogManager.getLogger(MainClass.class.getName());


    public static void main(String[] args) {

        final String INPUT_FILE_NAME;
        final String OUTPUT_FILE_NAME;


        try {
            if (args.length == TWO_ARGUMENTS) {
                logger.info("There are 2 args. Program starts with file input");

                INPUT_FILE_NAME = args[FIRST_ARGUMENT];
                OUTPUT_FILE_NAME = args[SECOND_ARGUMENT];

            } else if (args.length == NO_ARGS) {
                logger.info("There are no args. Program starts with console input");

                INPUT_FILE_NAME = null;
                OUTPUT_FILE_NAME = null;

            } else {
                throw new ArgsAmountException("bad number of args");
            }


            //here we are getting input from required reader
            ArrayList<String> listOfInput = MyExecutor.createReader(INPUT_FILE_NAME);
            if (listOfInput == null) {
                throw new IOException("smth wrong with input");
            }


            //создается список Data из входящих данных
            ArrayList<Data> dataArrayList;
            dataArrayList = Data.createDataList(listOfInput);

            MyExecutor executor = new MyExecutor(OUTPUT_FILE_NAME, dataArrayList);

            //executing Calculator
            executor.executeCalculator();


        } catch (ArgsAmountException exception) {
            logger.error("Wrong amount of arguments given", exception);
            return;
        }
        catch (IOException e) {
            logger.error(e);
            return;
        }
        catch (Exception e) {
            logger.error("error in executing command", e);
        }
    }

}
