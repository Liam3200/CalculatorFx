package com.liam.calculatorapp;

public class Calculation {
    private static Calculation instance = new Calculation();

    private Calculation() {
    }

    public static Calculation getInstance() {
        return instance;
    }

    public double calculate(int calcType, double a, double b) {
        return switch (calcType) {
            case 1 -> subtract(a, b);
            case 2 -> add(a, b);
            case 3 -> multiply(a, b);
            case 4 -> divide(a, b);
            default -> 0;
        };
    }

    private double add(double a,double b) {
        return a+b;
    }

    private double subtract(double a, double b) {
        return a-b;
    }

    private double multiply(double a, double b) {
        return a*b;
    }

    private double divide(double a, double b) {
        return a/b;
    }
}
