package ru.luxoft.cources;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        //Карта покупателей с картой покупок покупателя
        TreeMap<String, TreeMap<String, Integer>> customers = new TreeMap<String, TreeMap <String, Integer>>();

        Scanner sc = new Scanner (System.in);

        while(sc.hasNext()) {
            String s = sc.nextLine();

            if(s.equals("exit")) {
                break;
            }

            String[] parts = s.split(" ");

            String customerName = parts[0];
            String goodName = parts[1];
            Integer count = Integer.parseInt(parts[2]);

            if (!customers.containsKey(customerName))
                customers.put(customerName, new TreeMap <String, Integer>());

            TreeMap <String, Integer> custTmp = customers.get(customerName);

            if (!custTmp.containsKey(goodName))
                custTmp.put(goodName,0);

            Integer oldCount = custTmp.get(goodName);

            custTmp.put(goodName, oldCount + count);
        }

        for(Map.Entry<String, TreeMap <String, Integer>> entry : customers.entrySet()) {
            String key = entry.getKey();
            TreeMap <String, Integer> value = entry.getValue();

            System.out.println(key + ":");

            for(Map.Entry<String,Integer> good : value.entrySet()) {
                String keyGood = good.getKey();
                Integer valueGood = good.getValue();

                System.out.println("     "+keyGood + " " + valueGood);
            }
        }
    }
}
