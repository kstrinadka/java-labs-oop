package com.StrinadkaKirill.javalabs.lab2.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader extends AbstractReader{
    @Override
    public ArrayList<String> getText() throws FileNotFoundException {

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


    public FileReader(String fileName) {
        super(fileName);
    }
}
