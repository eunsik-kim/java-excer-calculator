package calculator;

import calculator.controller.CalculateController;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        CalculateController controller = new CalculateController();
        controller.calculate();
    }
}
