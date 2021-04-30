package ru.luxoft.cources;

import java.util.Arrays;
import java.util.Comparator;

public class GenericListAggregator<T extends Number> {
    // 3
    private final T[] arr;

    // 4
    public GenericListAggregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Массив не должен быть null");
        }

        this.arr = arr;
    }
    // 5
    public double getAvgValue() {
        double sum = 0.0;
        for (T t : arr) {
            sum += t.doubleValue();
        }
        return sum / arr.length;
    }

    // 6
    public double getMaxValue() {
        return getLastValue(Comparator.naturalOrder());
    }

    public double getMinValue() {
        return getLastValue(Comparator.reverseOrder());
    }

    private double getLastValue(Comparator<Double> comparator) {
        double maxValue = arr[0].doubleValue();

        for (T num : arr) {
            if (comparator.compare(num.doubleValue(), maxValue) > 0) {
                maxValue = num.doubleValue();
            }
        }
        return maxValue;
    }

    // 7

    public void printArrayInfo() {
        System.out.println("Array " + arr.getClass().getTypeName());
        System.out.println(Arrays.toString(arr));
        System.out.println("max  :  " + getMaxValue());
        System.out.println("min  :  " + getMinValue());
        System.out.println("avg  :  " + getAvgValue());
    }

}
