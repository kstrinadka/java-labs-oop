package com.StrinadkaKirill.javalabs.lab2.myCommand;


import com.StrinadkaKirill.javalabs.lab2.MyContext;

import java.util.ArrayList;

//у этой штуки должен быть доступ к мапе с переменными, список ее аргументов,
//доступ к стеку, чтобы команда сама забирала от-туда и сама обратно клала
// по итогу внутри ссылка на мапу, ссылка на стек
public abstract class AbstractCommand {

    protected MyContext context;

    protected ArrayList<String> arguments;


    public AbstractCommand(MyContext context, ArrayList<String> arguments) {
        this.context = context;
        this.arguments = arguments;
    }


    //this is operation that can do each command
    public abstract void doOperation() throws Exception;


    @Override
    public String toString() {
        return "AbstractCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
