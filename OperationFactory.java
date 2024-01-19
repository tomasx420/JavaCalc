public class OperationFactory {
    private final CalculatorConfiguration configuration;

    public OperationFactory(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public Operation createOperation(String operationType) {
        switch (operationType) {
            case "+":
                return new AddOperation(configuration);
            case "-":
                return new SubtractOperation(configuration);
            case "*":
                return new MultiplyOperation(configuration);
            case "/":
                return new DivideOperation(configuration);
            default:
                throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }
    }
}