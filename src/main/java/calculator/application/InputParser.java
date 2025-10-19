package calculator.application;

import calculator.application.provided.InputParserUseCase;
import calculator.domain.Delimiters;
import calculator.domain.Numbers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser implements InputParserUseCase {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.*)\\\\n(.*)");

    @Override
    public Numbers parse(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력된 문자열이 없습니다.");
        }
        Matcher matcher = CUSTOM_PATTERN.matcher(input);
        if (matcher.matches()) {
            String delimitersPart = matcher.group(1);
            String numbersPart = matcher.group(2);
            if (numbersPart.isBlank()) {
                throw new IllegalArgumentException("입력된 문자열이 없습니다.");
            }
            Delimiters delimiters = parseDelimiters(delimitersPart);
            return parseNumbers(numbersPart, delimiters);
        }
        // 기본 구분자
        Delimiters delimiters = new Delimiters(List.of(',', ':'));
        return parseNumbers(input, delimiters);
    }

    private Delimiters parseDelimiters(String delimitersPart) {
        List<Character> delimiters = new java.util.ArrayList<>(delimitersPart.chars()
                .mapToObj(c -> (char) c)
                .toList());
        delimiters.addAll(List.of(',', ':'));
        return new Delimiters(delimiters);
    }

    private Numbers parseNumbers(String numbersPart, Delimiters delimiter) {
        List<Integer> numbers =  Arrays.stream(numbersPart.split(delimiter.getRegex()))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("숫자가 아닌 입력값: " + s);
                    }
                })
                .toList();
        return new Numbers(numbers);
    }

}
