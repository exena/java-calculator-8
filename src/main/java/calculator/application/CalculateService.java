package calculator.application;

import calculator.application.provided.CalculateUseCase;
import calculator.domain.Numbers;

public class CalculateService implements CalculateUseCase {

    @Override
    public int calculate(Numbers numbers) {
        return numbers.sum();
    }

}