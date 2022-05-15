package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Statistics;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Обрабатывает и сохраняет статистику игры
 */
public class Statistic {


    ArrayList<StatRecord> statRecordArrayList;

    File currentStatisticFile;

    //определим по режиму игры
    String fileName;


    /**
     * Режим игры, для которого сохраняется статистика
     */
    private GameModes currentGameMode;






    public void doSomthing() {
        this.currentStatisticFile = null;

        ArrayList<String> list = new ArrayList<>();

        try {
            list = getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inputDataProcessing(list);

        clear();
    }

    /**
     * добавление новой записи о завершенной игре к общему списку записей.
     */
    public void addRecord(StatRecord statRecord) {

    }


    /**
     * уничтожение всех записей статистики перед новой записью
     */
    public void clear() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("file.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }


    private int getTimeInSeconds() {


        return 0;
    }


    /**
     * Узнаем в какой режим мы играли, чтобы добавить запись в статистику
     */
    public GameModes getGameMode (int widthOfField, int heightOfField, int numberOfMines) {

        if (widthOfField == 9 && heightOfField == 9 && numberOfMines == 10) {
            return GameModes.BEGINNER;
        }
        else if (widthOfField == 16 && heightOfField == 16 && numberOfMines == 40) {
            return GameModes.INTERMEDIATE;
        }
        else if (widthOfField == 30 && heightOfField == 16 && numberOfMines == 99) {
            return GameModes.BEGINNER;
        }
        else {
            return GameModes.SPECIFIC;
        }
    }


    /**
     * Метод считывает файл статистики и создает необработанный список результатов String
     * @return - список String считанный с нужного файла статистики (или пустой список)
     * @throws IOException
     */
    public ArrayList<String> getText() throws IOException {


        //Если нет такого файла, то создаем файл и возвращаем пустой список
        if (!(new File("file.txt").isFile())) {

            System.out.println("нет такой файл");
            ArrayList<String> list = new ArrayList<>();
            try {
                File file = new File("file.txt");
                file.createNewFile();
                System.out.println("Empty File Created:- " + file.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }
        else {
            System.out.println("Есть такой файл");
        }


        //если есть файл, то считываем с него строки и пихаем в list

        ArrayList<String> list = new ArrayList<>();


        try {
            this.currentStatisticFile = new File("file.txt");
            Scanner myReader = new Scanner(this.currentStatisticFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;

    }



    /**
     * Обрабатываются поступившие из текстового файла данные
     */
    private void inputDataProcessing(ArrayList<String> list) {
        //Нужно достать время из строки



        System.out.println("длина списка = " + list.size());
        for (String str: list) {
            System.out.println(str);
        }

    }





}
