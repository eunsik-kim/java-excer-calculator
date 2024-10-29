package calculator.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Calculator {
    Integer sumArray(Integer[] integers) {
        return Arrays.stream(integers)
                .mapToInt(Integer::intValue)
                .sum();
    }
}