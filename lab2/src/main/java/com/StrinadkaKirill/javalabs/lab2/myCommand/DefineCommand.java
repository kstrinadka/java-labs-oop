package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException.DefineException;

import java.util.ArrayList;
import static com.StrinadkaKirill.javalabs.lab2.Constants.*;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий AbstractCommand
 */
public class DefineCommand extends AbstractCommand {



    @Override
    public void doOperation() throws  RuntimeException{
        if (arguments.size() != TWO_ARGUMENTS) {
            throw new ArgsAmountException("pop");
        }


        String str = arguments.get(FIRST_ARGUMENT);
        double value;

        if (Character.isLetter(str.charAt(FIRST_LETTER))) {
            value = checkNumerOrVariable(arguments.get(SECOND_ARGUMENT));
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
