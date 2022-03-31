package com.StrinadkaKirill.javalabs.lab2.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriterMy extends MyAbstractWriter {

    @Override
    public void writeResult() throws IOException {


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String str: stack) {
            writer.write(str + "\n");
        }

        writer.close();


    }


    public FileWriterMy(String fileName, ArrayList<String> stack) {
        super(fileName, stack);
    }
}
