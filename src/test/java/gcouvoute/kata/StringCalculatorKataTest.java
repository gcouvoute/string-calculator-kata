package gcouvoute.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorKataTest {

    StringCalculatorKata stringCalculatorKata = new StringCalculatorKata();

    @ParameterizedTest
    @CsvSource(
            delimiter = ';',
            value = {
                    ";0",
                    "0;0",
                    "1;1",
                    "0,0;0",
                    "1,2;3",
            }
    )
    void step1(String numbers, int expected) {
        assertThat(stringCalculatorKata.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            delimiter = ';',
            value = {
                    "1,1;2",
                    "1,1,2;4",
                    "1,1,2,4;8",
                    "1,1,2,4,8;16"
            }
    )
    void step2(String numbers, int expected) {
        assertThat(stringCalculatorKata.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            delimiter = ';',
            value = {
                    "'1\n1';2",
                    "'1,1\n2';4",
                    "'1\n1,2,4\n8';16"
            }
    )
    void step3(String numbers, int expected) {
        assertThat(stringCalculatorKata.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                    "1\n,1",
                    "1,1,\n2",
                    "1\n,1,2,4,\n8"
            }
    )
    void step3WithFailure(String numbers) {
        assertThatThrownBy(() -> stringCalculatorKata.add(numbers)).isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {
                    "'//;\n'|0",
                    "'//;\n1;1'|2",
                    "'//;\n1;1\n2'|4",
                    "'//n\n1n1n2n4'|8",
                    "'//,\n1\n1,2,4\n8'|16"
            }
    )
    void step4(String numbers, int expected) {
        assertThat(stringCalculatorKata.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            delimiter = ';',
            value = {
                    "1,-1;-1",
                    "1,1,-2;-2",
                    "1,-1,2,-4;-1, -4",
                    "-1,-1,2,4,8;-1, -1"
            }
    )
    void step5WithFailure(String numbers, String negatives) {
        assertThatThrownBy(() -> stringCalculatorKata.add(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("negatives not allowed: %s", negatives);
    }
}