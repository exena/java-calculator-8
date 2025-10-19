package calculator;

import calculator.adapter.ConsoleInputHandler;
import calculator.adapter.ConsoleOutputHandler;
import calculator.application.CalculateService;
import calculator.application.InputParser;
import calculator.application.provided.CalculateUseCase;
import calculator.application.provided.InputParserUseCase;
import calculator.domain.Numbers;

public class Application {
    public static void main(String[] args) {
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
        InputParserUseCase parser = new InputParser();
        CalculateUseCase calculateService = new CalculateService();

        String input = inputHandler.readInput();
        Numbers numbers = parser.parse(input);
        int result = calculateService.calculate(numbers);
        outputHandler.printResult(result);
    }
}
