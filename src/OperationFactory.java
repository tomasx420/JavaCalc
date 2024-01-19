package src;
import java.util.Map;
import java.util.HashMap;

public class OperationFactory {
    private Map<String, Operation> operations;

    public OperationFactory(CalculatorConfiguration configuration) {
        operations = new HashMap<>();
        // store operations in a map
        operations.put("+", new AddOperation(configuration));
        operations.put("-", new SubtractOperation(configuration));
        operations.put("*", new MultiplyOperation(configuration));
        operations.put("/", new DivideOperation(configuration));
    }

    public Operation createOperation(String operationType) {
        // when an operation requested - return operation from map
        return operations.get(operationType);
    }
}