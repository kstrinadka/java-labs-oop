package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model;

import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.Cell;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellConditions;
import com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell.CellState;
import javafx.animation.Timeline;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Field {



    //Timeline timeline;

    //LocalTime time = LocalTime.parse("00:00:00");

    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    //должны поступать из вне
    private int widthOfField = 9;       //x
    private int heightOfField = 9;      //y
    private int numberOfMines = 10;

    private int flagsAmount;


    ArrayList<Cell> mainField = new ArrayList<>();

    public ArrayList<Cell> getMainField() {
        return mainField;
    }


    public Field() {

        String gameMode = choosingGameMode();

        createMainField();
        setMines();
        setNumbersForWholeFieldAroundMines();


    }

    public Field(int height, int width, int minesAmount) {
        //field = new int[width][height];
        this.heightOfField = height;
        this.widthOfField = width;

        this.numberOfMines = minesAmount;
        this.flagsAmount = minesAmount;

        //fillTheFieldByRandom(minesAmount);

        createMainField();
        setMines();
        setNumbersForWholeFieldAroundMines();
    }



    /**
     * Создает поле, в котором каждая клетка закрыта и без мины
     */
    private void createMainField () {
        this.mainField = new ArrayList<>(Arrays.asList(new Cell[this.heightOfField*this.widthOfField]));

        System.out.println("capacity of field = " + this.mainField.size());

        for (int i = 0; i < this.heightOfField; ++i) {
            for (int j = 0; j < this.widthOfField; ++j) {
                this.mainField.set(this.widthOfField*i + j, new Cell(j, i, CellConditions.CLOSED, false));
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


    //вроде это не надо
    public void setCell(int y_coord, int x_coord, boolean hasMine) {
        Cell cell = new Cell(CellConditions.CLOSED, true);
        this.mainField.set(y_coord*this.widthOfField + x_coord, cell);
    }


    /**
     * установить мину в заданных координатах
     */
    private void setOneMine(int y_coord, int x_coord) {

        Cell cell = new Cell(x_coord, y_coord, CellConditions.CLOSED, true);
        this.mainField.set(y_coord*this.widthOfField + x_coord, cell);
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
    //вроде это не нужно


    /**
     * Расставляет мины на заданном поле случайным образом
     * Сейчас мины могут несколько раз в одно и то же место ставится
     */
    void setMines() {
        Random random = new Random();
        int n = this.numberOfMines;

        myBreakLabel:
        while (n > 0) {
            for (int i = 0; i < this.heightOfField; i++)
                for (int j = 0; j < this.widthOfField; j++)
                {
                    int x = random.nextInt(100);
                    if (x % 99 == 0) {
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
    public boolean checkMineHere (int y_coord, int x_coord) {
        Cell oneCell = getCell(y_coord, x_coord);
        return oneCell.cellHasMine();
    }


    /**
     * получаем клетку с поля по координатам. (можно для проверки есть там мина или нет)
     * @return
     */
    public Cell getCell(int y_coord, int x_coord) {
        if (y_coord >= heightOfField || x_coord >= widthOfField || y_coord < 0 || x_coord < 0) {
            System.out.println("нерправильные координаты!!! (" + x_coord + ", " + y_coord + ") ");
        }

        Cell oneCell = this.mainField.get(y_coord*this.widthOfField + x_coord);
        return oneCell;
    }


    /**
     * метод изменения состояния ячейки по индексу {x, y} на заданное состояние.
     * @param cellState - состояние ячейки, которое мы установим в заданные координаты
     */
    void setCell(int x, int y, CellState cellState) {

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


    /**
     * Создает поле для режима Specific
     */
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


    /**
     * Расставляет цифры вокруг мин на всем поле
     */
    private void setNumbersForWholeFieldAroundMines() {
        for (int i = 0; i < this.heightOfField; ++i) {
            for (int j = 0; j < this.widthOfField; j++) {
                Cell a = this.mainField.get(this.widthOfField*i + j);
                if (a.cellHasMine()) {
                    System.out.println("добавление цифры");

                    addNumberAruondOneMine(j, i);
                }
            }
        }
    }


    /**
     * Добавляет единичку вокруг конкретной мины
     */
    private void addNumberAruondOneMine (int x_coord, int y_coord) {

        if (x_coord < 0 || y_coord < 0) {
            return;
        }

        plusOneToConcretCell(x_coord - 1, y_coord - 1);
        plusOneToConcretCell(x_coord - 1, y_coord);
        plusOneToConcretCell(x_coord, y_coord - 1);
        plusOneToConcretCell(x_coord - 1, y_coord + 1);
        plusOneToConcretCell(x_coord + 1, y_coord - 1);
        plusOneToConcretCell(x_coord + 1, y_coord + 1);
        plusOneToConcretCell(x_coord + 1, y_coord);
        plusOneToConcretCell(x_coord, y_coord + 1);


    }

    /**
     *  Делает +1 в эту клетку
     */
    private void plusOneToConcretCell (int x_coord, int y_coord) {
        if (x_coord < 0 || y_coord < 0 || x_coord >= widthOfField || y_coord >= heightOfField) {
            return;
        }

        Cell a = getCell(y_coord, x_coord);
        if (a.cellHasMine()) {
            return;
        }

        a.plusOneToCell();
    }


    public int getFlagsAmount() {
        return this.flagsAmount;
    }

    public int getNumberOfMines() {
        return this.numberOfMines;
    }

    /**
     * @return Успех или неуспех операции
     */
    public boolean decreaseFlagsAmount() {
        if (this.flagsAmount <= 0) {
            return false;
        }
        this.flagsAmount--;
        return true;
    }

    /**
     * @return  Успех или неуспех операции
     */
    public boolean increaseFlagsAmount() {
        if (this.flagsAmount >= this.numberOfMines) {
            return false;
        }
        this.flagsAmount++;
        return true;
    }

    public void resetFlagsAmount () {
        this.flagsAmount = this.numberOfMines;
    }


    /**
     * При открытии пустой ячейки – автоматически открываются все соседние пустые ячейки, так же дополнительно
     * открываются прилежащие информационные ячейки (ячейки с количеством заминированных соседей).
     */
    private void openNearbyEmptyCells(Cell cell) {
        int x_coord = cell.getX_coordinate();
        int y_coord = cell.getY_coordinate();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                int new_x_coord = x_coord + j;
                int new_y_coord = y_coord + i;
                if (new_x_coord >= 0 && new_y_coord >= 0 && new_y_coord < heightOfField && new_x_coord <widthOfField) {
                    Cell newCell = getCell(new_y_coord, new_x_coord);
                    if (newCell.getState().equals(CellConditions.CLOSED) && !newCell.cellHasMine())
                    {
                        openCell(newCell);
                    }

                }
            }
        }


    }

    public void deleteFlag(Cell cell) {
        cell.deleteFlag();
        increaseFlagsAmount();
    }

    public void setFlag(Cell cell) {
        cell.setFlag();
        decreaseFlagsAmount();
    }


    /**
     * открыть ячейку и проверить ближайшие
     */
    public void openCell(Cell cell) {
        cell.openThisCell();


        if (cell.getCellNumber() == 0 && !cell.cellHasMine()) {
            openNearbyEmptyCells(cell);
        }


    }


    /**
     * пометить ячейку, с заданными координатами, как ячейку содержащую мину.
     * открытие помеченной ячейки в игровом процессе будет запрещено до тех пор, пока метка не снята.
     */
    public void markCell(int x, int y) {

    }


    /**
     * убрать флаг с ячейки
     */
    public void unmarkCell(int x, int y) {

    }


    /**
     * метод проверки расстановки флагов.
     * Возвращаемое значение: true – если все метки на поле расставлены верно, false – в противном случае.
     */
    public boolean flagsCorrect() {

        return true;
    }










}
