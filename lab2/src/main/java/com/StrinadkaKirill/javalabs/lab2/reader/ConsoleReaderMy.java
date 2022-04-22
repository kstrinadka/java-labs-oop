package com.StrinadkaKirill.javalabs.lab2.reader;

import com.StrinadkaKirill.javalabs.lab2.MainClass;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleReaderMy extends MyAbstractReader {

    private static final Logger logger = LogManager.getLogger(MainClass.class.getName());

    @Override
    public ArrayList<String> getText() throws IOException {

        logger.info("getting text from console reader");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("""
                Input your text here:
                to stop write "."
                """);

        String string = reader.readLine();
        ArrayList<String> list = new ArrayList<>();

        while (!string.equals(".")) {
            list.add(string);
            string = reader.readLine();
        }

        return list;
    }


    public ConsoleReaderMy(String fileName) {
        super(fileName);
    }
}
