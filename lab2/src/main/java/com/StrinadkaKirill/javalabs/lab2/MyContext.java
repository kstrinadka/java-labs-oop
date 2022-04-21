package com.StrinadkaKirill.javalabs.lab2;

import com.StrinadkaKirill.javalabs.lab2.myExceptions.StackException;

import java.util.ArrayList;
import java.util.TreeMap;

public class MyContext {

    //stack for calculator
    private final ArrayList<String> stack;

    //here will be variables that defining in executing
    private final TreeMap<String, Double> defineVariables;


    //for console input
    public MyContext(ArrayList<String> stack, TreeMap<String, Double> defineVariables) {
        this.stack = stack;
        this.defineVariables = defineVariables;
    }



    //поменять потом на свои исключения
    public String pop() throws StackException {
        if (stack != null && !stack.isEmpty()) {
            String element = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return element;
        }
        else {
            StackException exception = new StackException("trying to get element from empty stack");
            throw exception;
        }
    }

    public String peekTop() throws StackException {
        if (stack != null && !stack.isEmpty()) {
            String element = stack.get(stack.size()-1);
            return element;
        }
        else {
            StackException exception = new StackException("trying to get element from empty stack");
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

    public boolean stackIsEmpty (){
        if (stack == null || stack.size() == 0) {
            return true;
        }
        else return false;
    }


    @Override
    public String toString() {
        return "MyContext{}";
    }
}
