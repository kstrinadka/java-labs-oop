package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;

import java.util.ArrayList;

public class PushCommand extends AbstractCommand{
    @Override
    public void doOperation() throws RuntimeException {

        if (arguments.size() != 1) {
            throw new ArgsAmountException("bad number of args in PushCommand");   //Заменить на свою ошибку
        }

        String a1 = arguments.get(0);

        context.push(a1);

    }

    public PushCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


    @Override
    public String toString() {
        return "PushCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
