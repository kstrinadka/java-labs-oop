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

        /*final String pathToFIle =
                String.format("out\\production\\lab1\\com\\StrinadkaKirill\\javalabs\\lab1\\%s", fileName);

        File file = new File(pathToFIle);
        Scanner input = new Scanner(file);

        readInputFile(list, input);*/


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

    private void readInputFile(ArrayList<String> list, Scanner input){
        while (input.hasNext()) {
            list.add(input.next());
        }
    }



    public FileReader(String fileName) {
        super(fileName);
    }
}
