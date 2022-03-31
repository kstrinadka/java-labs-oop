package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class PlusCommand extends AbstractCommand{
    @Override
    public void doOperation() throws Exception {

        if (arguments !=  null) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }

        String a1 = context.pop();
        String a2 = context.pop();

        //проверить не переменнная ли a1 и a2


        double number1 = checkNumerOrVariable(a1);
        double number2 = checkNumerOrVariable(a2);

        String result = String.valueOf(number1 + number2);
        context.push(result);

    }


    public PlusCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
