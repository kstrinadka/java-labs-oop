package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell;


//будет хранить разные состояния ячейки и ее координаты


public class Cell {


    private int x_coordinate;
    private int y_coordinate;

    //мб сделать enum для возможных состояний ячейки
    private CellConditions condition;

    boolean hasMine;


    public Cell(CellConditions condition, boolean hasMine) {
        this.condition = condition;
        this.hasMine = hasMine;
    }

    public Cell() {
        this.condition = CellConditions.CLOSED;
        this.hasMine = false;
    }

    @Override
    public String toString() {

        if (this.hasMine) {
            return "9";
        }
        else return "0";
    }


    /**
     * @return - есть мина в этой клетке или нет
     */
    public boolean cellHasMine() {
        return this.hasMine;
    }

}
