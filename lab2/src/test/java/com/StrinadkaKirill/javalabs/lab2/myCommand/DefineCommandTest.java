package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.data.Data;
import com.StrinadkaKirill.javalabs.lab2.myExceptions.ArgsAmountException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


// получить ArrayList<>() для стека. Думаю можно из текст блока
// создать свои stack и defineVariables
// создать здесь свой контекст
// получить список аргументов
//


class DefineCommandTest {


    @Test
    void doOperation() {
        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("a");
        arguments.add("4");

        DefineCommand command = new DefineCommand(context, arguments);

        command.doOperation();

        double number1 = context.getDefine("a");
        System.out.println(number1);

        assertEquals(number1, 4.0);
    }

}


