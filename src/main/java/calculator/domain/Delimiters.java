package calculator.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Delimiters {
    List<Character> values;

    public Delimiters(List<Character> values) {
        if (values.stream().anyMatch(Character::isDigit)) {
            throw new IllegalArgumentException("커스텀 구분자에 숫자가 들어갔습니다.");
        }
        this.values = values;
    }

    public String getRegex() {
        String result = values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        return "[" + result + "]";
    }
}
