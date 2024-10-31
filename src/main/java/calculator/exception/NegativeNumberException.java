package calculator.exception;

public class NegativeNumberException extends InvalidInputException {
    public NegativeNumberException(String inputStr) {
        super("음수는 입력할 수 없습니다.", inputStr);
    }
}
