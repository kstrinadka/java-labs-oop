package com.StrinadkaKirill.javalabs.lab2.writer;

import java.io.IOException;
import java.util.ArrayList;

public class ConsoleWriterMy extends MyAbstractWriter {

    @Override
    public void writeResult() throws IOException {

        System.out.println("\nhere is stack after executing:");
        for (String str: stack) {
            System.out.println(str);
        }



    }


    public ConsoleWriterMy(String fileName, ArrayList<String> stack) {
        super(fileName, stack);
    }
}
