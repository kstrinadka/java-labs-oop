package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Field {


    //должны поступать из вне
    private int widthOfField = 9;       //y
    private int heightOfField = 9;      //x
    private int numberOfMines = 10;


    ArrayList<Cell> mainField = new ArrayList<>();


    public Field() {

        String gameMode = choosingGameMode();

        createMainField();
        setMines();           //пока что ставим 10 мин на поле 9*9

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


    /** установить мину в заданных координатах
     * @param x
     * @param y
     */
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
     * Сейчас мины могут несколько раз в одно и то же место ставится
     */
    void setMines() {
        Random random = new Random();
        int n = this.numberOfMines;

        myBreakLabel:
        while (n > 0) {
            for (int i = 1; i <= this.heightOfField; i++)
                for (int j = 1; j <= this.widthOfField; j++)
                {
                    int x = random.nextInt(23);
                    if (x % 7 == 0) {
                        //проверяем нет ли уже тут мины и только потом ее ставим
                        if (!checkMineHere(i,j)) {
                            setOneMine(i,j);
                            n--;
                            if (n < 1) {
                                break myBreakLabel;
                            }
                        }

                    }
                }
        }
    }


    /**
     * Проверяем клетку с координатами x y на мину
     * @return - оказалась ли здесь мина
     */
    boolean checkMineHere (int x, int y) {
        Cell oneCell = getCell(x, y);
        return oneCell.cellHasMine();
    }


    /**
     * получаем клетку с поля по координатам. (можно для проверки есть там мина или нет)
     * @return
     */
    Cell getCell(int x, int y) {
        x--;
        y--;
        Cell oneCell = this.mainField.get(x*this.widthOfField + y);

        return oneCell;
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

        Scanner myInput = new Scanner( System.in );
        int gameMode = 0;
        gameMode = myInput.nextInt();

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

        setFieldSizes(nameOfGameMode);

        return nameOfGameMode;
    }


    /** задает параметры поля по игровому режиму
     * @param gameMode - выбранный пользователем игровой режим
     */
    private void setFieldSizes(String gameMode) {

        switch (gameMode) {
            case "Beginner":  {
                this.widthOfField = 9;
                this.heightOfField = 9;
                this.numberOfMines = 10;
            }
                break;
            case "Intermediate":  {
                this.widthOfField = 16;
                this.heightOfField = 16;
                this.numberOfMines = 40;
            }
                break;
            case "Profesional":  {
                this.widthOfField = 30;
                this.heightOfField = 16;
                this.numberOfMines = 99;
            }
                break;
            case "Specific":  {
                installationSpecificMode();
            }
                break;

            default: {
                System.out.println("не сработал почему-то никакой из вариантов при задании размеров поля");
                throw new MissingFormatArgumentException(gameMode);
            }
        }


    }

    private void installationSpecificMode() {
        Scanner myInput = new Scanner( System.in );
        int widthOfField = 0, heightOfField = 0;

        System.out.println("""
                put your field height and width here...              
                """);


        widthOfField = myInput.nextInt();
        heightOfField = myInput.nextInt();

        int numberOfMines = 0;
        System.out.println("""
                put your number of mines here...              
                """);

        numberOfMines = myInput.nextInt();


        if (widthOfField == 0 || heightOfField == 0 || numberOfMines == 0) {
            throw new RuntimeException("bad arguments for Specific mode");
        }

        this.widthOfField = widthOfField;
        this.heightOfField = heightOfField;
        this.numberOfMines = numberOfMines;
    }


}
