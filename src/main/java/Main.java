package main.java;

public class Main  {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator(200, "AAA");
        CalculatorView calculatorView = new CalculatorView(calculator);
    }
}