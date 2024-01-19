import java.math.BigDecimal;
import java.math.RoundingMode;

interface Operation {
    double execute(double a, double b);
}

class AddOperation implements Operation {
    private final CalculatorConfiguration configuration;

    public AddOperation(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public double execute(double a, double b) {
        double result = a + b;
        if (!configuration.allowNegativeResults() && result < 0) {
            throw new IllegalArgumentException("Negative results are not allowed.");
        }
        result = new BigDecimal(result).setScale(configuration.resultPrecision(), RoundingMode.HALF_UP).doubleValue();
        return result;
    }
}

class SubtractOperation implements Operation {
    private final CalculatorConfiguration configuration;

    public SubtractOperation(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public double execute(double a, double b) {
        double result = a - b;
        if (!configuration.allowNegativeResults() && result < 0) {
            throw new IllegalArgumentException("Negative results are not allowed.");
        }
        result = new BigDecimal(result).setScale(configuration.resultPrecision(), RoundingMode.HALF_UP).doubleValue();
        return result;
    }
}

class MultiplyOperation implements Operation {
    private final CalculatorConfiguration configuration;

    public MultiplyOperation(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public double execute(double a, double b) {
        double result = a * b;
        if (!configuration.allowNegativeResults() && result < 0) {
            throw new IllegalArgumentException("Negative results are not allowed.");
        }
        result = new BigDecimal(result).setScale(configuration.resultPrecision(), RoundingMode.HALF_UP).doubleValue();
        return result;
    }
}

class DivideOperation implements Operation {
    private final CalculatorConfiguration configuration;

    public DivideOperation(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public double execute(double firstOperand, double secondOperand) {
        if (secondOperand == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        double result = firstOperand / secondOperand;
        if (!configuration.allowNegativeResults() && result < 0) {
            throw new IllegalArgumentException("Negative results are not allowed.");
        }
        result = new BigDecimal(result).setScale(configuration.resultPrecision(), RoundingMode.HALF_UP).doubleValue();
        return result;
    }
}