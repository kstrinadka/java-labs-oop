package com.StrinadkaKirill.javalabs.lab1;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * MyReader reads input file by words and save theirs to output ArrayList
 */
public class MyReader {


    static ArrayList<String> openAndReadFromFile (String fileName) throws FileNotFoundException {

        ArrayList<String> list = new ArrayList<>();

        final String pathToFIle =
                String.format("out\\production\\lab1\\com\\StrinadkaKirill\\javalabs\\lab1\\%s", fileName);

        File file = new File(pathToFIle);
        Scanner input = new Scanner(file);

        readInputFile(list, input);

        return list;
    }


    static void readInputFile(ArrayList<String> list, Scanner input){
        while (input.hasNext()) {
            list.add(input.next());
        }
    }

}
