public class CalculatorConfiguration {
    private final boolean allowNegativeResults;
    private final int resultPrecision;

    private CalculatorConfiguration(Builder builder) {
        this.allowNegativeResults = builder.allowNegativeResults;
        this.resultPrecision = builder.resultPrecision;
    }

    public boolean allowNegativeResults() {
        return allowNegativeResults;
    }

    public int resultPrecision() {
        return resultPrecision;
    }

    public static class Builder {
        private boolean allowNegativeResults;
        private int resultPrecision;

        public Builder allowNegativeResults(boolean allowNegativeResults) {
            this.allowNegativeResults = allowNegativeResults;
            return this;
        }

        public Builder resultPrecision(int resultPrecision) {
            this.resultPrecision = resultPrecision;
            return this;
        }

        public CalculatorConfiguration build() {
            return new CalculatorConfiguration(this);
        }
    }
}