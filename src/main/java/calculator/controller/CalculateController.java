package calculator.controller;

import calculator.model.CalculatorService;
import calculator.view.ConsoleView;

import java.util.Scanner;

public class CalculateController {
    private final CalculatorService calculatorService;
    public CalculateController () {
        this.calculatorService = CalculatorService.createService();
    }
    public void calculate() {
        String inputStr = ConsoleView.getInput();
        Integer result = calculatorService.stringAdd(inputStr);
        ConsoleView.printOutput(result);
    }
}
