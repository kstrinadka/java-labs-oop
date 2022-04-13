package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class PrintCommand extends AbstractCommand{
    @Override
    public void doOperation() throws RuntimeException {

        System.out.println(context.peekTop());

    }

    public PrintCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


    @Override
    public String toString() {
        return "PrintCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
