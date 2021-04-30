package ru.luxoft.cources;

public class Main {

    public static void main(String[] args) {
        MyArrayList1<Jeans> myArrayList = new MyArrayList1<>();
        MyArrayList1<Integer> myArrayList2 = new MyArrayList1<>();
        int j = 65;
        float p = 30;
        StringBuilder jeansNamePrefix = new StringBuilder("AAA");

        for (int i = 0; i < 10; i++) {
            Jeans jeans = new Jeans(jeansNamePrefix.append((char) j).toString(), "XXX", p + j++, i);
            myArrayList.add(jeans);
        }

        print(myArrayList);

        myArrayList.remove(0);
        myArrayList.remove(5);
        myArrayList.remove(12);
        print(myArrayList);

        System.out.println("----------------- myArrayList2 -------------------");
        myArrayList2.add(1);
        myArrayList2.add(2);
        myArrayList2.add(3);
        myArrayList2.add(4);
        myArrayList2.add(5);
        myArrayList2.add(6);
        myArrayList2.add(7);
        myArrayList2.add(8);
        myArrayList2.add(9);
        myArrayList2.add(10);
        myArrayList2.add(11);
        myArrayList2.add(12);
        myArrayList2.add(13);
        myArrayList2.add(14);
        myArrayList2.add(15);


        print(myArrayList2);
        myArrayList2.remove(0);
        myArrayList2.remove(5);
        myArrayList2.remove(12);

        print(myArrayList2);

    }

    private static void print(MyArrayList1<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
