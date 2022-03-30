package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class PopCommand extends AbstractCommand {
    @Override
    public void doOperation() throws Exception {

        if (arguments.size() != 0) {
            throw new IllegalArgumentException();   //Заменить на свою ошибку
        }




        //проверить не пустой ли стек иначе бросить ошибку

        context.pop();

    }

    public PopCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }
}
