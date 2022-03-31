package com.StrinadkaKirill.javalabs.lab2;

import java.util.ArrayList;
import java.util.TreeMap;

public class MyContext {

    private String inputFileName;

    private String outputFileName;

    //stack for calculator
    private ArrayList<String> stack;

    //here will be variables that defining in executing
    private TreeMap<String, Double> defineVariables;


    //for console input
    public MyContext(ArrayList<String> stack, TreeMap<String, Double> defineVariables) {
        this.stack = stack;
        this.defineVariables = defineVariables;
    }

    //for input from file
    public MyContext(String inputFileName, String outputFileName, ArrayList<String> stack, TreeMap<String, Double> defineVariables) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.stack = stack;
        this.defineVariables = defineVariables;
    }


    //поменять потом на свои исключения
    public String pop() throws Exception {
        if (stack != null && !stack.isEmpty()) {
            String element = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return element;
        }
        else {
            Exception exception = new Exception("trying to get element from empty stack");      //заменить на свой exception
            throw exception;
        }
    }

    public String peekTop() throws Exception {
        if (stack != null && !stack.isEmpty()) {
            String element = stack.get(stack.size()-1);
            return element;
        }
        else {
            Exception exception = new Exception("trying to get element from empty stack");
            throw exception;
        }
    }

    public void push(String element) {
        stack.add(element);
    }

    public void setDefine(String key, double value) {
        defineVariables.put(key, value);
    }

    public boolean containsVariable(String key) {
        return defineVariables.containsKey(key);
    }

    public double getDefine(String key) {

        //проверить есть ли вообще такое значение, иначе бросить ошибку
        try {
            if (defineVariables == null) {
                throw new Exception("map is not available");
            }
            if (!defineVariables.containsKey(key)) {
                throw new Exception("can't find specified key in map");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defineVariables.get(key);
    }


    @Override
    public String toString() {
        return "MyContext{}";
    }
}
