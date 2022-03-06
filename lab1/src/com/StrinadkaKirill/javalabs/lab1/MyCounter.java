package com.StrinadkaKirill.javalabs.lab1;


import java.util.*;

public class MyCounter {

    private final TreeMap<String, Integer> wordsWithFrequency;

    private ArrayList<MyData> wordsWithFrequencyList;

    private final int totalWords;

    public int getTotalWords() {
        return totalWords;
    }

    public ArrayList<MyData> getWordsWithFrequencyList() {
        return wordsWithFrequencyList;
    }


    /**
     * @param list - input ArrayList with all words that may be repeated
     * MyCounter calculates the number of words and their relative frequency
     */
    public MyCounter(ArrayList<String> list) {

        totalWords = list.size();

        wordsWithFrequency = new TreeMap<>();

        for (String word: list) {
            if (wordsWithFrequency.containsKey(word)) {
                int value = wordsWithFrequency.get(word);
                value++;

                wordsWithFrequency.replace(word, value);
            }
            else {
                wordsWithFrequency.put(word, 1);
            }
        }

        fromMapToList();
    }


    private void fromMapToList () {

        wordsWithFrequencyList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordsWithFrequency.entrySet()) {
            MyData tmp = new MyData(entry.getKey(),  entry.getValue());
            wordsWithFrequencyList.add(tmp);
        }

        Collections.sort(wordsWithFrequencyList);

        Collections.reverse(wordsWithFrequencyList);
    }

    //custom structure for word and value
    public static final class MyData implements Comparable<MyData> {
        private final String word;
        private final Integer frequency;

        private MyData(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }


        public int frequency() {
            return frequency;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (MyData) obj;
            return this.word.equals(that.word) &&
                    this.frequency.equals(that.frequency) ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, frequency);
        }

        @Override
        public String toString() {
            return word + "," + frequency;
        }

        @Override
        public int compareTo(MyData wordWithFrequency) {
            return this.frequency.compareTo(wordWithFrequency.frequency());
        }
    }

    public void printList () {
        for (MyData str : wordsWithFrequencyList) {
            System.out.println(str);
        }
    }

}



