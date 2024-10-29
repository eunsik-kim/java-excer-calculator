package calculator.model;

import java.sql.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorParser {
    public String[] parseInput(String inputStr) {
        if (inputStr == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        String delimiters = ",|:"; // 기본 구분자는 , or :
        String numberStr = inputStr;

        if (inputStr.startsWith("//")) {
            CustomDelimiterResult result = parseCustomDelimiter(inputStr);
            delimiters = String.format("%s|%s", delimiters, Pattern.quote(result.delimiter));
            numberStr = result.numbers;
        }

        return splitWithDelimiter(numberStr, delimiters);
    }
    private CustomDelimiterResult parseCustomDelimiter(String inputStr) {
        //  Custom 구분자는 // 와 \n 사이에 존재
        Pattern pattern = Pattern.compile("//(.*?)\\\\n(.*)");
        Matcher matcher = pattern.matcher(inputStr);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid custom delimiter format");
        }

        return new CustomDelimiterResult(
                matcher.group(1),  // delimiter
                matcher.group(2)   // numbers
        );
    }

    private String[] splitWithDelimiter(String inputStr, String delimiters) {
        return inputStr.split(delimiters);
    }

    private record CustomDelimiterResult(String delimiter, String numbers) {
    }
}
