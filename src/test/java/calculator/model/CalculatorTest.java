package calculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("정수 배열의 합을 계산한다")
    void sumArray() {
        // given
        Integer[] numbers = {1, 2, 3, 4, 5};

        // when
        int result = calculator.sumArray(numbers);

        // then
        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("빈 배열의 합은 0이다")
    void sumEmptyArray() {
        // given
        Integer[] numbers = {};

        // when
        int result = calculator.sumArray(numbers);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("null이 포함된 배열은 예외가 발생한다")
    void sumArrayWithNull() {
        // given
        Integer[] numbers = {1, null, 3};

        // when & then
        assertThrows(NullPointerException.class, () -> {
            calculator.sumArray(numbers);
        });
    }

    @Test
    @DisplayName("음수를 포함한 배열의 합을 계산한다")
    void sumArrayWithNegative() {
        // given
        Integer[] numbers = {-1, -2, 3, 4};

        // when
        int result = calculator.sumArray(numbers);

        // then
        assertThat(result).isEqualTo(4);
    }
}