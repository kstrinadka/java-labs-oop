package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Statistics;


/**
 * Одна запись в таблице статистики
 */
public class StatRecord {


    /**
     * Режим игры данной записи (вроде не нужно)
     */
    private  GameModes gameMode;


    /**
     * Время игры
     */
    private String timeString;


    /**
     * Время игры в секундах
     */
    private int timeSeconds;


    /**
     * 1), 2), 3), 4), 5)
     */
    private String placeInStatistic;




    private int parseStringTimeToSeconds () {

        return 0;
    }

    @Override
    public String toString() {
        return  gameMode +
                ", time: " + timeString + '\'';
    }
}
