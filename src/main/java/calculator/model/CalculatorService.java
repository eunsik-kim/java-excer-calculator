package calculator.model;

import java.util.Arrays;

public class CalculatorService {
    private final Calculator calculator;
    private final CalculatorParser calculatorParser;

    public CalculatorService(Calculator calculator, CalculatorParser calculatorParser) {
        this.calculator = calculator;
        this.calculatorParser = calculatorParser;
    }

    public static CalculatorService createService() {
        return new CalculatorService(new Calculator(), new CalculatorParser());
    }

    public Integer stringAdd(String inputStr) {
        String[] parsedStrArr = this.calculatorParser.parseInput(inputStr);
        Integer[] parsedNumArr = Arrays.stream(parsedStrArr)
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        return this.calculator.sumArray(parsedNumArr);
    }
}
