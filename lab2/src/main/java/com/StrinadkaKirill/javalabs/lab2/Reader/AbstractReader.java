package com.StrinadkaKirill.javalabs.lab2.Reader;

import java.io.IOException;
import java.util.ArrayList;

// return Data of read text from console or from file
public abstract class AbstractReader {


    final String fileName;


    public AbstractReader(String fileName) {
        this.fileName = fileName;
    }

    public abstract ArrayList<String> getText() throws IOException;
}
