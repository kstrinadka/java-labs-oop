package com.StrinadkaKirill.javalabs.lab2;

public class MainClass {


    public static void main(String[] args) {


        final String INPUT_FILE_NAME;


        try {
            if (args.length == 1) {
                INPUT_FILE_NAME = args[0];

                System.out.println("input from file\n");
            } else if (args.length == 0) {
                INPUT_FILE_NAME = null;

                System.out.println("std console input\n");
            } else {
                throw new IllegalArgumentException("bad number of args");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }


    }

}
