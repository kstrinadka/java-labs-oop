package com.StrinadkaKirill.javalabs.lab2.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleReaderMy extends MyAbstractReader {


    @Override
    public ArrayList<String> getText() throws IOException {


        System.out.println("we are in console reader\n");

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
