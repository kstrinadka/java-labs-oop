package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

public class CommentCommand extends AbstractCommand {
    @Override
    public void doOperation() {

    }

    public CommentCommand(MyContext context, ArrayList<String> arguments) {
        super(context, arguments);
    }


}
