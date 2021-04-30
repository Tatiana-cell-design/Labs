package ru.luxoft.cources;

import java.util.Arrays;

public class MyArrayList1<T> {
    // 3
    private static final int INIT_SIZE = 16;
    private static final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    // 4
    // 5
    private void resize(int newLength) {
        array = Arrays.copyOf(array, newLength);
    }
    // 6

    public void add(T item) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = item;
    }

    // 7
    public T get(int index) {
        @SuppressWarnings("unchecked")
        T t = (T) array[index];
        return t;
    }

    // 8
    public int size() {
        return pointer;
    }

    // 9
    public void remove(int index) {
        if (pointer - index >= 0) {
            System.arraycopy(array, index + 1, array, index, pointer - index);
        }
        array[pointer--] = null;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем
        // длина массива, то уменьшу в два раза
    }


}

