package ru.luxoft.cources;

public class MyArrayList1<T> {
    // 3
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;
    // 4
    /*
    public void add(T item) {
        array[pointer++] = item;
    }
    */
    // 5
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
    // 6

    public void add(T item) {

        if(pointer == array.length-1)
            resize(array.length*2);
        array[pointer++] = item;
    }

    // 7
    public T get(int index) {
        return (T) array[index];
    }
    // 8
    public int size() {
        return pointer;
    }
    // 9
    public void remove(int index) {
        for (int i = index; i<pointer; i++)
            array[i] = array[i+1];
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length/2); // если элементов в CUT_RATE раз меньше чем
        // длина массива, то уменьшу в два раза
    }


}

