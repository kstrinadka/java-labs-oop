package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class SubCommand extends AbstractCommand {
    @Override
    public void doOperation() throws Exception {

        if (arguments != null) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }

        String a1 = context.pop();
        String a2 = context.pop();


        double number1 = checkNumerOrVariable(a1);
        double number2 = checkNumerOrVariable(a2);

        String result = String.valueOf(number1 - number2);
        context.push(result);

    }

    public SubCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
