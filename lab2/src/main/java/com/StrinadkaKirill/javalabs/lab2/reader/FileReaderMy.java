package com.StrinadkaKirill.javalabs.lab2.reader;

import com.StrinadkaKirill.javalabs.lab2.MainClass;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderMy extends MyAbstractReader {

    private static final Logger logger = LogManager.getLogger(MainClass.class.getName());

    @Override
    public ArrayList<String> getText() {

        logger.info("getting text from file reader");

        ArrayList<String> list = new ArrayList<>();

        try {
            File myObj = new File("text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;

    }


    public FileReaderMy(String fileName) {
        super(fileName);
    }
}
