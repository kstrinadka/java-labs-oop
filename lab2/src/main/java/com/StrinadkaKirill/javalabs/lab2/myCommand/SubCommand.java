package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class SubCommand extends AbstractCommand {
    @Override
    public void doOperation() throws Exception {

        if (arguments.size() != 0) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }

        String a1 = context.pop();
        String a2 = context.pop();

        //проверить не переменнная ли a1 и a2

        double number1 = Double.parseDouble(a1);
        double number2 = Double.parseDouble(a2);

        String result = String.valueOf(number1 - number2);
        context.push(result);

    }

    public SubCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
