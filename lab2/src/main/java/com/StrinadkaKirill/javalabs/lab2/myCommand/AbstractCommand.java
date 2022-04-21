package com.StrinadkaKirill.javalabs.lab2.myCommand;


import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;


public abstract class AbstractCommand {

    protected MyContext context;

    protected ArrayList<String> arguments;


    public AbstractCommand(MyContext context, ArrayList<String> arguments) {
        this.context = context;
        this.arguments = arguments;
    }


    //this is operation that can do each command
    public abstract void doOperation() throws RuntimeException;


    @Override
    public String toString() {
        return "AbstractCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }


    //checking element from stack. Is it double or defined variable?
    protected double checkNumerOrVariable(String variable) {

        double number1;
        boolean numeric;
        numeric = variable.matches("-?\\d+(\\.\\d+)?");

        if(numeric){
            number1 = Double.parseDouble(variable);
        }
        else{
            number1 = context.getDefine(variable);
        }

        return number1;
    }
}
