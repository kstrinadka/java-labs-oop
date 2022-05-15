package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.Model.Statistics;


/**
 * Одна запись в таблице статистики
 */
public class StatRecord implements Comparable<StatRecord> {


    /**
     * Время игры
     */
    private String timeString;


    /**
     * Время игры в секундах
     */
    private int timeSeconds;



    public StatRecord(String timeString) {
        this.timeString = timeString;

        this.timeSeconds = parseStringTimeToSeconds();
    }

    public int getTimeSeconds() {
        return this.timeSeconds;
    }

    public int parseStringTimeToSeconds () {

        String[] h1=this.timeString.split(":");

        int minute=Integer.parseInt(h1[0]);
        int second=Integer.parseInt(h1[1]);

        int temp;
        temp = second + (60 * minute);

        System.out.println("seconds: " + temp);

        return temp;
    }

    @Override
    public String toString() {
        return "time: " + timeString;
    }

    @Override
    public int compareTo(StatRecord o) {
        return Integer.compare(getTimeSeconds(), o.getTimeSeconds());
    }
}
