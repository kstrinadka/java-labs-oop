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


        //проверить буква ли a1
        //проверить буква ли то, что второй аргумент
        //заменить магические числа на константы

        //проверить не задефайнена ли уже эта переменная
        //ничего страшного. Передефайнится

        String str = arguments.get(0);
        double value;

        if (Character.isLetter(str.charAt(0))) {
            value = checkNumerOrVariable(arguments.get(1));
        }
        else {
            //System.out.println("defining variables must start from letter");
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
