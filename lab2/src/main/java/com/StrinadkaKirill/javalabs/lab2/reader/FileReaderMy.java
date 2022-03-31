package com.StrinadkaKirill.javalabs.lab2.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderMy extends MyAbstractReader {
    @Override
    public ArrayList<String> getText() {

        System.out.println("we are in file reader\n");
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
