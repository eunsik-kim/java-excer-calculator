package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
    @Nested
    @DisplayName("문자열 계산기 테스트")
    class StringCalculatorTest {

        @Nested
        @DisplayName("정상 입력 테스트")
        class NormalInputTest {
            @ParameterizedTest
            @DisplayName("기본 구분자(콤마, 콜론) 테스트")
            @CsvSource({
                    "1:2:3, 6",
                    "1,2,3, 6",
                    "1:2,3, 6",
                    "1,2:3,4:5, 15",
                    "10:20,30, 60"
            })
            void normal_구분자_테스트(String input, int expected) {
                assertSimpleTest(() -> {
                    run(input);
                    assertThat(output()).contains("결과 : " + expected);
                });
            }

            @ParameterizedTest
            @DisplayName("커스텀 구분자 테스트")
            @CsvSource({
                    "//;\\n1;2;3, 6",
                    "//+\\n1+2+3, 6",
                    "//*\\n1*2*3, 6",
                    "//[]\\n1[]2[]3, 6",
                    "//$\\n1$2$3, 6"
            })
            void 커스텀_구분자_테스트(String input, int expected) {
                assertSimpleTest(() -> {
                    run(input);
                    assertThat(output()).contains("결과 : " + expected);
                });
            }
        }

        @Nested
        @DisplayName("예외 상황 테스트")
        class ExceptionTest {
            @ParameterizedTest
            @DisplayName("음수 입력 테스트")
            @ValueSource(strings = {
                    "-1,2,3",
                    "1,-2,3",
                    "1,2,-3"
            })
            void 음수_입력_테스트(String input) {
                assertSimpleTest(() ->
                        assertThatThrownBy(() -> runException(input))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("음수는 허용되지 않습니다")
                );
            }

            @ParameterizedTest
            @DisplayName("잘못된 구분자 형식 테스트")
            @ValueSource(strings = {
                    "//\\n\\n1\\n2\\n3",
                    "//3\\n1323",
                    "//;1;2;",
                    "/;\\n;1;2;",
                    "//\\n1,2,3"
            })
            void 잘못된_구분자_테스트(String input) {
                assertSimpleTest(() ->
                        assertThatThrownBy(() -> runException(input))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("올바른 형식이 아닙니다")
                );
            }
        }

        @Nested
        @DisplayName("빈 입력 테스트")
        class EmptyInputTest {
            @ParameterizedTest
            @DisplayName("빈 문자열 입력시 0 반환")
            @ValueSource(strings = {
                    "",
                    "\n",
                    "//;\\n",
                    "//+\\n"
            })
            void 빈_입력_테스트(String input) {
                assertSimpleTest(() -> {
                    run(input);
                    assertThat(output()).contains("결과 : 0");
                });
            }
        }

        @Nested
        @DisplayName("경계값 테스트")
        class BoundaryTest {
            @Test
            @DisplayName("큰 숫자 입력 테스트")
            void 큰_숫자_테스트() {
                assertSimpleTest(() -> {
                    run("1000,2000,3000");
                    assertThat(output()).contains("결과 : 6000");
                });
            }

            @Test
            @DisplayName("단일 숫자 입력 테스트")
            void 단일_숫자_테스트() {
                assertSimpleTest(() -> {
                    run("1");
                    assertThat(output()).contains("결과 : 1");
                });
            }

            @Test
            @DisplayName("최대 길이 입력 테스트")
            void 최대_길이_테스트() {
                String longInput = String.join(",", Collections.nCopies(1000, "1"));
                assertSimpleTest(() -> {
                    run(longInput);
                    assertThat(output()).contains("결과 : 1000");
                });
            }
        }
    }

    @Nested
    @DisplayName("특수한 케이스 테스트")
    class SpecialCaseTest {
        @ParameterizedTest
        @DisplayName("정규표현식 특수문자 구분자 테스트")
        @ValueSource(strings = {
                "//\\\\n1\\\\2\\\\3",  // 백슬래시를 구분자로
                "//.\\n1.2.3",         // 점을 구분자로
                "//^\\n1^2^3",         // 캐럿을 구분자로
                "//$\\n1$2$3",         // 달러를 구분자로
                "//|\\n1|2|3"          // 파이프를 구분자로
        })
        void 정규표현식_특수문자_테스트(String input) {
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 6");
            });
        }
    }
}
