package com.StrinadkaKirill.javalabs.lab2.writer;

import java.io.IOException;
import java.util.ArrayList;

public abstract class MyAbstractWriter {

    final String fileName;

    final ArrayList<String> stack;


    public MyAbstractWriter(String fileName, ArrayList<String> stack) {
        this.fileName = fileName;
        this.stack = stack;
    }

    public abstract void writeResult() throws IOException;
}
