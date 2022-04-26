package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.Random;

public class Field {


    //должны поступать из вне
    private int widthOfField = 9;       //y
    private int heightOfField = 9;      //x


    ArrayList<Cell> mainField = new ArrayList<>();


    public Field() {
        createMainField();
        setMines(10);           //пока что ставим за мин на поле 9*9

    }


    /**
     * Создает поле, в котором каждая клетка закрыта и без мины
     */
    private void createMainField () {
        this.mainField = new ArrayList<>(Arrays.asList(new Cell[this.heightOfField*this.widthOfField]));

        System.out.println("capacity of field = " + this.mainField.size());

        for (int i = 0; i < this.heightOfField; ++i) {
            for (int j = 0; j < this.widthOfField; j++) {
                this.mainField.set(this.widthOfField*i + j, new Cell(CellConditions.CLOSED, false));
            }
        }
    }


    /**
     * печатает поле в консоль (пока что использую для отладки)
     */
    public void printMainField() {
        for (int i = 0; i < this.heightOfField; ++i) {
            for (int j = 0; j < this.widthOfField; j++) {
                Cell a = this.mainField.get(this.widthOfField*i + j);
                System.out.print(a + " ");
            }
            System.out.print("\n");
        }
    }


    public void setCell(int x, int y, boolean hasMine) {
        //если координаты начинаются с 0, то отнимаем 1
        x = y = x-1;

        Cell cell = new Cell(CellConditions.CLOSED, true);

        this.mainField.set(x*this.widthOfField + y, cell);
    }

    private void setOneMine(int x, int y) {
        //если координаты начинаются с 0, то отнимаем 1
        x = x - 1;
        y = y - 1;

        Cell cell = new Cell(CellConditions.CLOSED, true);

        this.mainField.set(x*this.widthOfField + y, cell);
    }


    // для обычных режимов кол-во мин опеределенное
    //для кастомного режима можно выбирать кол-во мин
    private int getNumberOfMines (String mode) {
        if (mode.equals("Beginner")){
            return 10;
        }
        if (mode.equals("Beginner")){
            return 40;
        }
        if (mode.equals("Beginner")){
            return 99;
        }

        //сделать, чтоб можно было выбрать, мб сюда уже должно кол-во поступать
        if (mode.equals("Specific")){
            return 10;
        }


        System.out.println("не прошли условия по кол-ву мин");
        return 10;
    }


    /**
     * Расставляет мины на заданном поле случайным образом
     * @param numberOfMines - количество мин
     */
    void setMines(int numberOfMines) {
        Random random = new Random();
        int n = numberOfMines;

        myBreakLabel:
        while (n > 0) {
            for (int i = 1; i <= this.heightOfField; i++)
                for (int j = 1; j <= this.widthOfField; j++)
                {
                    int x = random.nextInt(23);
                    if (x % 7 == 0) {
                        //set mine
                        setOneMine(i,j);
                        n--;
                        if (n < 1) {
                            break myBreakLabel;
                        }
                    }
                }
        }
    }


    /**
     * Выбираем здесь режим игры
     * @return - строка с названием режима игры
     */
    public String choosingGameMode() {
        System.out.println("""
                press 1 to choose "Beginner"
                press 2 to choose "Intermediate"
                press 3 to choose "Profesional"
                press 4 to choose "Specific"
                
                """);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        int gameMode = 0;
        String input = "none";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("error in reading game mode");
            e.printStackTrace();
        }

        gameMode = Integer.parseInt(input);

        if (gameMode == 0) {
            throw new MissingFormatArgumentException("wrong name of game mode");
        }

        String nameOfGameMode = switch (gameMode) {
            case 1 -> "Beginner";
            case 2 -> "Intermediate";
            case 3 -> "Profesional";
            case 4 -> "Specific";
            //возможно тут надо кидать ошибку с неправильным названием команды
            default -> null;
        };

        if (nameOfGameMode == null) {
            throw new MissingFormatArgumentException("wrong name of game mode");
        }

        System.out.println("you chose " + nameOfGameMode + " gamemode");
        return nameOfGameMode;
    }
}
