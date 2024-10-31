package calculator.exception;

// 구체적인 예외 클래스들 (자식)
public class InvalidInputException extends CalculatorException {
    public InvalidInputException(String inputStr) {
        super("잘못된 입력입니다." + "입력 : " + inputStr);
    }

    public InvalidInputException(String message, String inputStr) {
        super(message + "입력 : " + inputStr);
    }
}
