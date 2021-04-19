package ru.luxoft.cources;

public class Main {

    public static void main(String[] args) {
        HashMapImpl hashMap = new HashMapImpl();

        hashMap.put(10, "Apple");
        hashMap.put(1, "Orange");
        hashMap.put(79, "Grape");
        System.out.println("Val at 79 "+hashMap.get(79));
        System.out.println("Val at 1 "+hashMap.get(1));
        System.out.println("Val at 10 "+hashMap.get(10));
        System.out.println("Val at 2 "+hashMap.get(2));
        hashMap.put(null, "Pear");
        System.out.println("Val at null "+hashMap.get(null));
        System.out.println("Hashmap has key at null:"+hashMap.containsKey(null));
        System.out.println("Hashmap has value at null:"+hashMap.containsValue("Pear"));
        System.out.println("Size of Map:"+hashMap.size());
        hashMap.remove(10);


        hashMap.remove(79);
        System.out.println("---------------" );
        System.out.println("Val at 79 "+hashMap.get(79));
        System.out.println("Val at 1 "+hashMap.get(1));
        System.out.println("Val at 10 "+hashMap.get(10));
        System.out.println("Val at 2 "+hashMap.get(2));

    }
}
