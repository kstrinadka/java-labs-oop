package com.StrinadkaKirill.javalabs.lab2.myCommand;

import com.StrinadkaKirill.javalabs.lab2.MyContext;
import com.StrinadkaKirill.javalabs.lab2.MyExecutor;
import com.StrinadkaKirill.javalabs.lab2.data.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MyCommandsTest {


    @Test
    void checkDefineCommand () {

        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("a");
        arguments.add("4");

        DefineCommand command = new DefineCommand(context, arguments);

        command.doOperation();

        double number1 = context.getDefine("a");

        assertEquals(number1, 4.0);
    }

    @Test
    void checkDivideCommand () {
        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        stack.add("13");
        stack.add("39");

        DivideCommand command = new DivideCommand(context, arguments);

        command.doOperation();
        String result = String.valueOf(39.0 / 13);

        assertEquals(context.pop(), result);
    }

    @Test
    void checkMultiplyCommand () {
        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        stack.add("13");
        stack.add("39");

        MultiplyCommand command = new MultiplyCommand(context, arguments);

        command.doOperation();
        String result = String.valueOf(39.0 * 13);

        assertEquals(context.pop(), result);

    }

    @Test
    void checkPlusCommand () {

        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        stack.add("13");
        stack.add("39");

        PlusCommand command = new PlusCommand(context, arguments);

        command.doOperation();
        String result = String.valueOf(39.0 + 13);

        assertEquals(context.pop(), result);
    }

    @Test
    void checkPopCommand () {

        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        context.push("test");
        context.push("349");

        PopCommand command = new PopCommand(context, arguments);

        command.doOperation();
        assertEquals(context.stackIsEmpty(), false);

        command.doOperation();
        assertEquals(context.stackIsEmpty(), true);

    }



    @Test
    void checkPushCommand () {

        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("test");

        PushCommand command = new PushCommand(context, arguments);

        assertEquals(context.stackIsEmpty(), true);
        command.doOperation();
        assertEquals(context.stackIsEmpty(), false);

        assertEquals(context.peekTop(), "test");

    }

    @Test
    void checkSqrtCommand () {
        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        stack.add("144");

        SqrtCommand command = new SqrtCommand(context, arguments);

        command.doOperation();
        String result = String.valueOf(Math.sqrt(144));

        assertEquals(context.peekTop(), result);

    }

    @Test
    void checkSubCommand () {
        ArrayList<String> stack = new ArrayList<>();
        TreeMap<String, Double> defineVariables = new TreeMap<>();
        MyContext context = new MyContext(stack, defineVariables);
        ArrayList<String> arguments = null;
        stack.add("13");
        stack.add("39");

        SubCommand command = new SubCommand(context, arguments);

        command.doOperation();
        String result = String.valueOf(39.0 - 13);

        assertEquals(context.pop(), result);

    }


    @Test
    void simpleTest () {
        String input = """
                # ответ: 3
                                
                DEFINE a 4
                PUSH a
                SQRT
                PRINT
                PUSH 5
                +
                PUSH 20
                -
                PUSH 1
                +
                PUSH 42
                /
                """;
        String result = "3.0";


        // To array
        String[] arr = input.split("\n");

        // To fixed-size list
        List<String> l = Arrays.asList(input.split("\n"));

        // To ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList(input.split("\n")));



        ArrayList<Data> dataArrayList = null;
        try {
            dataArrayList = Data.createDataList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }


        MyExecutor executor = new MyExecutor(null, dataArrayList);

        String fromStack = executor.executeCalculator();

        assertEquals(fromStack, result);

    }


}
