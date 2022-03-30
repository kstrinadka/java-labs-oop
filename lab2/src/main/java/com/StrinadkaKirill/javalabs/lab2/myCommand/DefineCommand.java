package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class DefineCommand extends AbstractCommand {
    @Override
    public void doOperation() throws Exception {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }

        String a1 = context.pop();
        String a2 = context.pop();

        //проверить буква ли a1

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
