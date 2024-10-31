package calculator.model.service;

import calculator.model.domain.Calculator;
import calculator.model.domain.CalculatorParser;
import calculator.model.domain.CalculatorValidator;

import java.util.Arrays;
import java.util.Objects;

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
        if (Objects.equals(parsedStrArr[0], "")) {
            return 0;
        }
        Integer[] parsedNumArr = Arrays.stream(parsedStrArr).map(str -> {
                                                CalculatorValidator.verifyPositiveInteger(str);
                                                return Integer.valueOf(str);
                                            }).toArray(Integer[]::new);
        return this.calculator.sumArray(parsedNumArr);
    }
}
