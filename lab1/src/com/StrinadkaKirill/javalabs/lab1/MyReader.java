package com.StrinadkaKirill.javalabs.lab1;

// возможно сделать статическим, чтобы он сразу в Counter передавал считанный вектор

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyReader {




    static boolean openAndReadFromFile (String fileName, ArrayList<String> list) throws FileNotFoundException {

        boolean readSuccessfully = false;

        File file = new File("out\\production\\lab1\\com\\StrinadkaKirill\\javalabs\\lab1\\input.txt");
        Scanner input = new Scanner(file);

        readInputFile(list, input);

        return readSuccessfully;
    }



    static void readInputFile(ArrayList<String> list, Scanner input){
        while (input.hasNext()) {
            list.add(input.next());
        }
    }


}
