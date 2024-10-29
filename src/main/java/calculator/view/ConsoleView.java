package calculator.view;

import java.util.Scanner;

public class ConsoleView {
    private static final String INPUT_STRING = "[덧셈계산기] 문자열을 입력해 주세요 : ";
    public static String getInput() {
        System.out.print(INPUT_STRING);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void printOutput(Integer result) {
        System.out.printf("결과 : %d", result);
    }
}
