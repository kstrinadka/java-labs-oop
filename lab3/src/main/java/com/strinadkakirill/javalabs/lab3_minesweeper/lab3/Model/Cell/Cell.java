package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell;


//будет хранить разные состояния ячейки и ее координаты


public class Cell {


    private int x_coordinate;
    private int y_coordinate;

    //мб сделать enum для возможных состояний ячейки
    private CellConditions condition;

    //показывает цифру в этой клетке (если 0, то это мина или пустая)
    private int cellNumber;

    boolean hasMine;


    public Cell(CellConditions condition, boolean hasMine) {
        this.condition = condition;
        this.hasMine = hasMine;
    }

    public Cell() {
        this.condition = CellConditions.CLOSED;
        this.hasMine = false;
    }

    public Cell(int x_coordinate, int y_coordinate, CellConditions condition, boolean hasMine) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.condition = condition;
        this.hasMine = hasMine;
    }

    public void plusOneToCell () {
        this.cellNumber++;
    }

    @Override
    public String toString() {

        if (this.hasMine) {
            return "9";
        }
        if (this.cellNumber > 0) {
            Integer a = this.cellNumber;
            return a.toString();
        }
        else return "0";
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }


    /**
     * @return - есть мина в этой клетке или нет
     */
    public boolean cellHasMine() {
        return this.hasMine;
    }

}
