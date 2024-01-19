public class ConsoleObserver implements CalculatorObserver {
    @Override
    public void updateResult(double result) {
        System.out.println("The result is: " + result);
    }
}