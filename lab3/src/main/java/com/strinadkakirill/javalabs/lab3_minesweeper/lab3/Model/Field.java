package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Field {


    //должны поступать из вне
    private int n = 9;
    private int m = 9;


    ArrayList<Cell> mainField = new ArrayList<>();


    public Field() {
        createMainField();
        setMines(10);           //пока что ставим за мин на поле 9*9

    }



    private void createMainField () {
        this.mainField = new ArrayList<>(Arrays.asList(new Cell[n*m]));
        //this.mainField.ensureCapacity(n*m);

        System.out.println("capacity of field = " + this.mainField.size());

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                this.mainField.set(m*i + j, new Cell(CellConditions.CLOSED, false));
            }
        }
    }



    public void printMainField() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                Cell a = this.mainField.get(n*i + j);
                System.out.print(a + " ");
            }
            System.out.print("\n");
        }
    }



    public void setCell(int x, int y, boolean hasMine) {
        //если координаты начинаются с 0, то отнимаем 1
        x = y = x-1;

        Cell cell = new Cell(CellConditions.CLOSED, true);

        this.mainField.set(x*n + y, cell);
    }

    private void setOneMine(int x, int y) {
        //если координаты начинаются с 0, то отнимаем 1
        x = x - 1;
        y = y - 1;

        Cell cell = new Cell(CellConditions.CLOSED, true);

        this.mainField.set(x*n + y, cell);
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


    /*
    Random ran = new Random();
    int x = ran.nextInt(6) + 5;

    The integer x is now the random number that has a possible outcome of 5-10.
     */

    /**
     * Расставляет мины на заданном поле случайным образом
     * @param numberOfMines - количество мин
     */
    void setMines(int numberOfMines) {
        Random random = new Random();
        int n = numberOfMines;

        while (n > 0) {
            for (int i = 1; i <= this.n; i++)
                for (int j = 1; j <= this.m; j++)
                {
                    int x = random.nextInt(23);
                    if (x % 7 == 0) {
                        //set mine
                        setOneMine(i,j);
                        n--;
                    }
                }
        }



    }



}
