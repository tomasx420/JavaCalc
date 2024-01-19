import java.util.Map;
import java.util.HashMap;

public class OperationFactory {
    private Map<String, Operation> operations;

    public OperationFactory(CalculatorConfiguration configuration) {
        operations = new HashMap<>();
        // Instead of creating new operations every time, we store them in a map
        operations.put("+", new AddOperation(configuration));
        operations.put("-", new SubtractOperation(configuration));
        operations.put("*", new MultiplyOperation(configuration));
        operations.put("/", new DivideOperation(configuration));
    }

    public Operation createOperation(String operationType) {
        // When an operation is requested, we return the operation from the map
        return operations.get(operationType);
    }
}