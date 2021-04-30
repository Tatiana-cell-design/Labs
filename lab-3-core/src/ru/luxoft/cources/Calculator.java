package ru.luxoft.cources;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    private String userInput;
    private final Scanner scanner;


    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public void calcProcess() {
        while (!"q".equals(userInput)) {
            try {
                System.out.println(calculationStep());

            } catch (InputMismatchException ex1) {
                System.out.println("Wrong input data!");
            } catch (DivByZeroException ex2) {
                System.out.println("Wrong second number for divide");
            }
        }
    }

    private double calculationStep() throws DivByZeroException, InputMismatchException {
        int firstNumber;
        int secondNumber;
        System.out.print("Enter number 1   = ");
        if (scanner.hasNextInt()) {
            firstNumber = scanner.nextInt();
        } else {
            userInput = scanner.nextLine();
            throw new InputMismatchException("Wrong input number 1");
        }
        System.out.print("Enter number 2   = ");
        if (scanner.hasNextInt()) {
            secondNumber = scanner.nextInt();
        } else {
            userInput = scanner.nextLine();
            throw new InputMismatchException("Wrong input number 2");
        }
        System.out.print("Enter operation  -> ");


        userInput = scanner.next();

        return calc(firstNumber, secondNumber);
    }

    private double calc(int firstNumber, int secondNumber) throws DivByZeroException {
        double result;
        switch (userInput) {
            case "+":
                result = plus(firstNumber, secondNumber);
                break;
            case "-":
                result = minus(firstNumber, secondNumber);
                break;
            case "*":
                result = ymn(firstNumber, secondNumber);
                break;
            case "/":
                if (secondNumber == 0) {
                    throw new DivByZeroException();
                }
                result = del(firstNumber, secondNumber);
                break;
            case "!":
                result = fact(firstNumber);
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }

    private int plus(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    private int minus(int numberOne, int numberTwo) {
        return numberOne - numberTwo;
    }

    private int ymn(int numberOne, int numberTwo) {
        return numberOne * numberTwo;
    }

    private int del(int numberOne, int numberTwo) {
        return numberOne / numberTwo;
    }

    private int fact(int numberOne) {
        int res = 1;
        for (int i = 1; i <= numberOne; i++) {
            res *= i;
        }

        return res;
    }

    public class DivByZeroException extends Exception {
        //
    }

}
