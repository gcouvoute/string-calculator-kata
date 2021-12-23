package gcouvoute.kata;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class StringCalculatorKata {

    int add(String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        }

        char delimiter = ',';
        String chars = numbers;

        if (numbers.matches("^//.\n[\\S\\s]*$")) {
            if (numbers.length() == 4) {
                return 0;
            }
            delimiter = numbers.charAt(2);
            chars = numbers.substring(4);
        }

        List<Integer> negatives = new ArrayList<>();

        Integer sum = Arrays.stream(chars.split("[\n" + delimiter + "]"))
                .map(Integer::parseInt)
                .map(integer -> {
                    if (integer < 0) {
                        negatives.add(integer);
                    }
                    return integer;
                })
                .reduce(0, Integer::sum);

        if (negatives.isEmpty()) {
            return sum;
        } else {
            StringJoiner joiner = new StringJoiner(", ");
            negatives.forEach(integer -> joiner.add(integer.toString()));
            throw new IllegalArgumentException("negatives not allowed: " + joiner);
        }
    }
}
