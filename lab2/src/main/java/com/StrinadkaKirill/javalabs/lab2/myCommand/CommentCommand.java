package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;


/**
 * Класс команды # стэкового калькулятора, имплементирующий AbstractCommand
 */
public class CommentCommand extends AbstractCommand {


    /**
     * Комментарий ничего не делает
     */
    @Override
    public void doOperation() {

    }

    public CommentCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


}
