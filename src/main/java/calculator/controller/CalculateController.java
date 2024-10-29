package calculator.controller;

import calculator.model.CalculatorService;

import java.util.Scanner;

public class CalculateController {
    private final CalculatorService calculatorService;
    public CalculateController () {
        this.calculatorService = CalculatorService.createService();
    }
    public void calculate() {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        System.out.printf("결과 : %s", calculatorService.stringAdd(inputStr));
    }
}
