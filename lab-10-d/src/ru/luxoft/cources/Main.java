package ru.luxoft.cources;

public class Main {

    public static void main(String[] args) {
        Integer[] intArr = {8, 11, 14, 18, 41, 35};
        GenericListAggregator<Integer> intAggregator = new GenericListAggregator<>(intArr);
        intAggregator.printArrayInfo();

        Double[] doubleArr = {4.6, 15.12, 18.32, 19.51, 32.11, 7.1};
        GenericListAggregator<Double> doubleAggregator = new GenericListAggregator<>(doubleArr);
        doubleAggregator.printArrayInfo();
    }

}
