package calculator.application.provided;

import calculator.domain.Numbers;

public interface InputParserUseCase {
    Numbers parse(String input);
}
