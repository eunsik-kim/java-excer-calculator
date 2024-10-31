package calculator.model.domain;

import calculator.model.domain.CalculatorParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorParserTest {
    private CalculatorParser calculatorParser;

    @BeforeEach
    void setUp() {
        this.calculatorParser = new CalculatorParser();
    }

    @ParameterizedTest
    @DisplayName("입력 string을 BaseDelimitor로 parsing하여 string 배열로 만든다")
    @CsvSource({
            "'1,2,3,4,5'",
            "'1,2,3:4:5'"
    })
    void parseStringWithBaseDelimitor(String input) {
        // when
        String[] result = calculatorParser.parseInput(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4", "5");
    }


    @ParameterizedTest
    @DisplayName("입력 string을 CustomDelimitor로 parsing하여 string 배열로 만든다")
    @CsvSource(value = {
            "'//.\\n1,2,3,4:5'",
            "'//!\\n1!2!3,4:5'"
    })
    void parseStringWithCustomDelimitor(String input) {
        // when
        String[] result = calculatorParser.parseInput(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4", "5");
    }

    @ParameterizedTest
    @DisplayName("빈 string을 입력받는 경우 빈 array를 반환하는지 확인")
    @ValueSource(strings = {"", "//!\\n"})
    void parseEmptyString(String input) {
        // when
        String[] result = calculatorParser.parseInput(input);

        // then
        assertThat(result).containsExactly("");

    }
    @Test
    @DisplayName("잘못된 커스텀 구분자 형식")
    void parseInvalidCustomDelimiter() {
        assertThrows(IllegalArgumentException.class, () ->
                calculatorParser.parseInput("//")
        );
    }
}