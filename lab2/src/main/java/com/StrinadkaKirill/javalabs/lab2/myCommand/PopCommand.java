package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;

import java.util.ArrayList;

public class PopCommand extends AbstractCommand {
    @Override
    public void doOperation() throws RuntimeException {

        if (arguments != null) {
            throw new ArgsAmountException("bad number of arguments in PopCommand");
        }


        context.pop();
    }

    public PopCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
