package src;
public class CalculatorContext {
    private Operation operation;
    private double firstNumber;
    private double secondNumber;
    private CalculatorObserver observer;

    public CalculatorContext(CalculatorObserver observer) {
        this.observer = observer;
    }

    public void setInput(double operand, Operation operation) {
        this.firstNumber = operand;
        this.operation = operation;
    }

    public void calculate(double operand) {
        this.secondNumber = operand;
        double result = operation.execute(firstNumber, secondNumber);
        observer.updateResult(result);
    }

    private CalculatorConfiguration configuration;

    public void setConfiguration(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }
}
