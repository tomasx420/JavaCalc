package src;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleCalculatorApp {
    public static void main(String[] args) {
        CalculatorObserver observer = new ConsoleObserver();
        CalculatorContext calculatorContext = new CalculatorContext(observer);
        Scanner scanner = new Scanner(System.in);

        int resultPrecision = -1;
        while (resultPrecision < 0) {
            System.out.print("Enter result precision: ");
            try {
                resultPrecision = scanner.nextInt();
                if (resultPrecision < 0) {
                    System.out.println("Invalid input. Please enter a non-negative number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard the invalid input
            }
        }

        boolean allowNegativeResults = false;
        while (true) {
            System.out.print("Allow negative results? (y/n): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                allowNegativeResults = true;
                break;
            } else if (input.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }

        // Set calc config
        CalculatorConfiguration config = new CalculatorConfiguration.Builder()
                .resultPrecision(resultPrecision)
                .allowNegativeResults(allowNegativeResults)
                .build();

        calculatorContext.setConfiguration(config);

        OperationFactory operationFactory = new OperationFactory(config);

        while (true) {
            System.out.print("Enter operation (+, -, *, /) or 'exit' to quit: ");
            String operationType = scanner.next();

            if (operationType.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator.");
                break;
            }

            try {

                Operation operation = operationFactory.createOperation(operationType);

                System.out.print("Enter first number: ");
                double operand = readOperand(scanner);

                calculatorContext.setInput(operand, operation);

                System.out.print("Enter second number: ");
                operand = readOperand(scanner);

                calculatorContext.calculate(operand);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }
    }

    private static double readOperand(Scanner scanner) {
        String input = scanner.next();
        try {
            // Try to parse with a period as the decimal separator
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // If that fails, try to parse with a comma as the decimal separator
            try {
                return Double.parseDouble(input.replace(',', '.'));
            } catch (NumberFormatException e2) {
                System.out.println("Invalid input. Please enter a number.");
                return readOperand(scanner); // Ask for input again
            }
        }
    }
}
