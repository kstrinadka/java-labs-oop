package com.StrinadkaKirill.javalabs.lab2.reader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * return Data of read text from console or from file
 */
public abstract class MyAbstractReader {

    final String fileName;

    public MyAbstractReader(String fileName) {
        this.fileName = fileName;
    }

    public abstract ArrayList<String> getText() throws IOException;
}
