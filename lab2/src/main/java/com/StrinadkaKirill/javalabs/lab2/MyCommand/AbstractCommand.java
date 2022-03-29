package com.StrinadkaKirill.javalabs.lab2.MyCommand;


import java.util.ArrayList;
import java.util.TreeMap;

//у этой штуки должен быть доступ к мапе с переменными, список ее аргументов,
//доступ к стеку, чтобы команда сама забирала от-туда и сама обратно клала
// по итогу внутри ссылка на мапу, ссылка на стек
public abstract class AbstractCommand {

    //from MyExecutor
    ArrayList<String> stack;

    //from MyExecutor
    TreeMap<String, Double> defineVariables;


    public AbstractCommand(ArrayList<String> stack, TreeMap<String, Double> defineVariables) {
        this.stack = stack;
        this.defineVariables = defineVariables;
    }

    
    //this is operation that can do each command
    public abstract void doOperation();
}
