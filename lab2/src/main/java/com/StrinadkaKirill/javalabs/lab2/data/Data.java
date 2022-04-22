package com.StrinadkaKirill.javalabs.lab2.data;



import com.StrinadkaKirill.javalabs.lab2.myCommand.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Преобразует строку со входа в данные, с которыми может работать калькулятор
 */
public class Data {

    private final String typeOfOperation;
    private final ArrayList<String> arguments;


    /**
     * тут обычная строка со входа превращается в разделенную на тип команды и аргументы
     * @param str
     * @throws IOException
     */
    public Data(String str) throws IOException {

        this.arguments = converteStringToList(str);
        this.typeOfOperation = getOperationNameFromString(str);

        if (this.typeOfOperation == null) {
            System.out.println();
            throw new IOException("smth wrong with operation type in Data constructor");
        }


    }

    /**
     * creating list of Data from input data
     * @param list - list of strings from input data
     * @return
     * @throws IOException
     */
    public static ArrayList<Data> createDataList(ArrayList<String> list) throws IOException {
        ArrayList<Data> dataArrayList = new ArrayList<>();
        for (String str: list) {
            Data element = new Data(str);
            dataArrayList.add(element);
        }

        return dataArrayList;
    }


    private ArrayList<String> converteStringToList(String str) {

        String[] strSplit = str.split("\\W+");
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(strSplit));

        if (arguments.size() > 1) {
            arguments.remove(0);
            return arguments;
        }
        else {
            return null;
        }
    }


    private String getOperationNameFromString(String str) {

        //возможно потом поменять регулярку на более общий разделитьель
        String[] strSplit = str.split(" ");
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(strSplit));

        String strType = arguments.get(0);


        return switch (strType) {
            case "" -> CommentCommand.class.getName();          //empty strings will be like comments
            case "#" -> CommentCommand.class.getName();
            case "/" -> DivideCommand.class.getName();
            case "*" -> MultiplyCommand.class.getName();
            case "+" -> PlusCommand.class.getName();
            case "-" -> SubCommand.class.getName();
            case "POP" -> PopCommand.class.getName();
            case "PUSH" -> PushCommand.class.getName();
            case "SQRT" -> SqrtCommand.class.getName();
            case "PRINT" -> PrintCommand.class.getName();
            case "DEFINE" -> DefineCommand.class.getName();

            //возможно тут надо кидать ошибку с неправильным названием команды
            default -> null;
        };

    }


    public String getCommandClassName() {
        return typeOfOperation;
    }

    public ArrayList<String> getArguments(){
        return arguments;
    }


    @Override
    public String toString() {
        return "Data{" +
                "typeOfOperation='" + typeOfOperation + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
