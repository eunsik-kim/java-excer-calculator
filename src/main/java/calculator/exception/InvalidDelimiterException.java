package calculator.exception;

public class InvalidDelimiterException extends InvalidInputException {
    public InvalidDelimiterException(String inputStr) {
        super("잘못된 구분자 형태 입니다.", inputStr);
    }
}
