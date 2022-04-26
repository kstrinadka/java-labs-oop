package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Cell;



// здесь будет храниться состояние ячейки (открыта, закрыта, флажок)
// и есть ли там мина или нет

/**
 * каждая клеточка имеет свое состояние
 * @param condition - открыта, закрыта, флажок
 * @param hasMine - есть ли мина
 */
public record CellState(CellConditions condition, boolean hasMine) {

}
