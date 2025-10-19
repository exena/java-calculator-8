package calculator.domain;

import java.util.List;

public class Numbers {
    private final List<Integer> values;

    public Numbers(List<Integer> values) {
        if (values.stream().anyMatch(n -> n < 0)) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
        this.values = values;
    }

    public int sum() {
        return values.stream().mapToInt(Integer::intValue).sum();
    }
}
