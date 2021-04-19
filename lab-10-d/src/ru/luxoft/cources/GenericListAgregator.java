package ru.luxoft.cources;

public class GenericListAgregator<T extends Number> {
    // 3
    T[] arr;
    // 4
    public GenericListAgregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Массив не должен быть null");
        }

        this.arr = arr;
    }
    // 5
    public double getAvgValue(){
        double sum = 0.0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i].doubleValue();
        }
        return sum/arr.length;
    }
    // 6
    public double getMaxValue(){
        double maxValue = arr[0].doubleValue();

        for (int i = 1; i < arr.length; i++){
            if (arr[i].doubleValue() > maxValue){
                maxValue = arr[i].doubleValue();
            }
        }

        return maxValue;
    }
    // 7
    public double getMinValue() {
        double minValue = arr[0].doubleValue();

        for (int i = 1; i < arr.length; i++) {
            if (minValue > arr[i].doubleValue()) {
                minValue = arr[i].doubleValue();
            }
        }
        return minValue;

    }

    public void printArrayInfo(){
        System.out.println("Array " + arr.getClass().getTypeName());
        System.out.println("max  :  " + getMaxValue());
        System.out.println("min  :  " + getMinValue());
        System.out.println("avg  :  " + getAvgValue());
    }

    public static void main(String[] args) {
        GenericListAgregator<Integer> intArr = new GenericListAgregator<>(new Integer[]{8, 11, 14, 18, 41, 35 });
        intArr.printArrayInfo();

        GenericListAgregator<Double> doubleArr = new GenericListAgregator<>(new Double[]{4.6, 15.12, 18.32, 19.51, 32.11, 7.1});
        doubleArr.printArrayInfo();

    }

}
