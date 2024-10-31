package calculator.exception;

public abstract class CalculatorException extends IllegalArgumentException {
    public CalculatorException(String message) {
        super(message);
    }
}

