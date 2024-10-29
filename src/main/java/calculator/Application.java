package calculator;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 1. 문자열 입력받기
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] stringNumbers  = parseDelimiter(input);
        System.out.printf("stringNumbers : %s\n", Arrays.toString(stringNumbers));
        System.out.printf("결과 : %d\n", calStringAdd(stringNumbers));
    }

    public static int[] parseDelimiter(String input) {
        // 2. 숫자 추출하기(값을 입력한 경우 IllegalArgumentException 을 발생)
        String[] stringNumArr = new String[0];
        String customDelimiter;
        // // 와 \n 사이에 위치하는 커스텀 구분자확인
        if (input.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*?)\\\\n(.*)");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                // 예약어가 사용되는 경우
                customDelimiter = Pattern.quote(matcher.group(1));
                String numbers = matcher.group(2);
                stringNumArr = numbers.split(customDelimiter);
            } else {
                throw new IllegalArgumentException("Not Number Input");
            }
        } else {
            // 기본 구분자는 , 와 : 이다
            String baseDelimiter= ",";
            if (input.contains(baseDelimiter)) {
                stringNumArr = input.split(baseDelimiter);
            } else {
                stringNumArr = input.split(":");
            }
        }

        try {
            int[] numArr = Arrays.stream(stringNumArr)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 양수로 구성되었는지 확인한다
            if (Arrays.stream(numArr).anyMatch(value -> value < 0)) throw new IllegalArgumentException("Not Positive Num");
            return numArr;
        } catch (NumberFormatException e)  {
            // 숫자로 구성되었는지 확인
            throw new IllegalArgumentException("Not Number Input", e);
        }
    }
    public static int calStringAdd(int[] numArr) {
        // 4. 아무 입력도 안들어갔을 경우 0을 출력한다
        return Arrays.stream(numArr).reduce(0, Integer::sum);
    }
}
