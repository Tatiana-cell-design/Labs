package ru.luxoft.cources;

//import java.lang.reflect.Array;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int PRODUCTS_LEN = 5;

    public static void main(String[] args) {
        // 3
        Scanner sc = new Scanner(System.in);
        // 5
        String[] masOfProducts = new String[PRODUCTS_LEN];
        Integer[] masOfCosts = new Integer[PRODUCTS_LEN];
        Integer[] masOfCounts = new Integer[PRODUCTS_LEN];

        int count = 0;
        while (sc.hasNext() && count < PRODUCTS_LEN) {
            String s = sc.nextLine();
            if ("exit".equals(s)) {
                break;
            }
            count++;
            // 4
            String[] parts = s.split(" ");
            // 15
            if (parts.length != 3) {
                System.out.println("Wrong number of arguments! Retry!");
                continue;
            }

            String productName = parts[0];
            Integer productCost;
            Integer productCount;
            try {
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Retry!");
                continue;
            }


            // 6
            boolean productAlreadyExists = false;
            for (int i = 0; i < PRODUCTS_LEN; i++) {
                if (productName.equals(masOfProducts[i])) {
                    masOfCosts[i] = productCost;
                    masOfCounts[i] += productCount;
                    productAlreadyExists = true;
                }
            }
            // 7
            if (!productAlreadyExists) {
                for (int i = 0; i < PRODUCTS_LEN; i++) {
                    if (masOfProducts[i] == null) {
                        masOfProducts[i] = productName;
                        masOfCosts[i] = productCost;
                        masOfCounts[i] = productCount;
                    }
                }
            }


        }
        // 8
        String[] sortedProducts = new String[PRODUCTS_LEN];
        System.arraycopy(masOfProducts, 0, sortedProducts, 0, PRODUCTS_LEN);
        // 9
        Arrays.sort(sortedProducts, String.CASE_INSENSITIVE_ORDER.thenComparing(String::compareTo));
        // 10
        System.out.println(Arrays.toString(sortedProducts));
        // 11
        long sum = 0;
        for (int i = 0; i < PRODUCTS_LEN; i++) {
            sum += (long) masOfCosts[i] * masOfCounts[i];
        }
        System.out.println(sum);

        // 12
        int indexOfMostPopularProduct = 0;
        for (int i = 0; i < PRODUCTS_LEN; i++) {
            if (masOfCounts[i] > masOfCounts[indexOfMostPopularProduct]) {
                indexOfMostPopularProduct = i;
            }
        }
        // 13 - 14
        for (int j = 0; j < 3; j++) {
            // int indexOfMostPopularProduct = 0;
            for (int i = 0; i < PRODUCTS_LEN; i++) {
                if (masOfCounts[i] > masOfCounts[indexOfMostPopularProduct]) {
                    indexOfMostPopularProduct = i;
                }
            }
            System.out.println("Most popular product is " + masOfProducts[indexOfMostPopularProduct]);
            masOfProducts[indexOfMostPopularProduct] = null;
            masOfCounts[indexOfMostPopularProduct] = 0;
            masOfCosts[indexOfMostPopularProduct] = 0;
        }


    }
}
