package calculator.model.domain;

import calculator.exception.InvalidInputException;
import calculator.exception.NegativeNumberException;

public class CalculatorValidator {
    // 양의 숫자로 이루어진 배열인지 확인
    public static void verifyPositiveInteger (String str) {
        try {
            int number = Integer.parseInt(str);
            if (number <= 0) {
                throw new NegativeNumberException(str);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("정수가 아닌 기호가 포함되어 있습니다. 입력: ", str);
        }
    }

    // 커스텀 구분자가 기호로 구성되어 있는지 확인
    public static void verifyCustomDelimiter (String delimiter) {
        if (!delimiter.matches("^[^0-9]+$")) {
            throw new InvalidInputException("커스텀 구분자는 숫자가 될 수 없습니다. 입력 : " + delimiter);
        }

        if (delimiter.contains("//") || delimiter.contains("\\n")) {
            throw new InvalidInputException("커스텀 구분자는 //이나 \\n이 될 수 없습니다. 입력 : " + delimiter);
        }
    }
}
