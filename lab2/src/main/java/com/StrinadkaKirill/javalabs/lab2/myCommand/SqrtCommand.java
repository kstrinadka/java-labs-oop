package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class SqrtCommand extends AbstractCommand{
    @Override
    public void doOperation() throws Exception {
        if (arguments != null) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }

        String a1 = context.pop();


        double number1 = checkNumerOrVariable(a1);


        if (number1 < 0.0) {
            throw new Exception("negative number sqrt"); //Заменить на свою ошибку
        }
        else {
            number1 = Math.sqrt(number1);
            String result = String.valueOf(number1);
            context.push(result);
        }
    }


    public SqrtCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


    @Override
    public String toString() {
        return "SqrtCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
