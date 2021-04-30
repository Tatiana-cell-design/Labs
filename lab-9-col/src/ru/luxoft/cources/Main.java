package ru.luxoft.cources;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        //Карта покупателей с картой покупок покупателя
        TreeMap<String, TreeMap<String, Integer>> customers = new TreeMap<>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String s = sc.nextLine();

            if (s.equals("exit")) {
                break;
            }

            String[] parts = s.split(" ");

            String customerName = parts[0];
            String goodName = parts[1];
            Integer count = Integer.parseInt(parts[2]);

            customers.putIfAbsent(customerName, new TreeMap<>());

            TreeMap<String, Integer> custTmp = customers.get(customerName);

            custTmp.putIfAbsent(goodName, 0);

            Integer oldCount = custTmp.get(goodName);

            custTmp.put(goodName, oldCount + count);
        }

        customers.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(
                    (k1, v1) -> System.out.println("     " + k1 + " " + v1)
            );
        });
    }
}
