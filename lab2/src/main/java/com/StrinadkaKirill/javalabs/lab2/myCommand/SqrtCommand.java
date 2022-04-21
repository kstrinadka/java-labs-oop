package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException.SqrtException;

import java.util.ArrayList;
import static com.StrinadkaKirill.javalabs.lab2.Constants.*;

/**
 * Класс команды "SQRT" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class SqrtCommand extends AbstractCommand{
    @Override
    public void doOperation() throws RuntimeException {
        if (arguments != null) {
            throw new ArgsAmountException("bad number of args in SqrtCommand: " + context.toString());
        }

        String a1 = context.pop();


        double number1 = checkNumerOrVariable(a1);


        if (number1 < DOUBLE_ZERO) {
            throw new SqrtException("negative number sqrt: " + context.toString());
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
