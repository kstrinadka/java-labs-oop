package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException.DivideByZeroException;

import java.util.ArrayList;

public class DivideCommand extends AbstractCommand{
    @Override
    public void doOperation() throws RuntimeException {
        if (arguments != null) {
            throw new ArgsAmountException("in DivideCommand: " + context.toString());
        }

        String a1 = context.pop();
        String a2 = context.pop();



        double number1 = checkNumerOrVariable(a1);
        double number2 = checkNumerOrVariable(a2);

        if (number2 == 0.0) {
            throw new DivideByZeroException("division by zero");
        }
        else {
            String result = String.valueOf(number1 / number2);
            context.push(result);
        }

    }

    public DivideCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
