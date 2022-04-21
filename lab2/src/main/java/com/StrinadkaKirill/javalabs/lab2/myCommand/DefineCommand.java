package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException.DefineException;

import java.util.ArrayList;

public class DefineCommand extends AbstractCommand {
    @Override
    public void doOperation() throws  RuntimeException{
        if (arguments.size() != 2) {
            throw new ArgsAmountException("pop");   //Заменить на свою ошибку
        }


        //заменить магические числа на константы


        String str = arguments.get(0);
        double value;

        if (Character.isLetter(str.charAt(0))) {
            value = checkNumerOrVariable(arguments.get(1));
        }
        else {
            throw new DefineException("incorrect name of defining variable" + context.toString());
        }


        context.setDefine(str, value);

    }


    public DefineCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


    @Override
    public String toString() {
        return "DefineCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
