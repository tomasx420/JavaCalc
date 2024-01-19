# Simple Java Console Calculator

The Simple Calculator System is a console-based application that allows users to perform basic arithmetic operations such as addition, subtraction, multiplication, and division. The application is implemented in Java using desing patterns. The calculator also supports configurable precision for the results and the ability to disallow/allow negative results.

### Repository Link: https://github.com/tomasx420/JavaCalc


## Instructions to run the calculator
First clone the project (`https://github.com/tomasx420/JavaCalc`)

Compile the all of the Java files in the src folder using this command: ``` javac *.java ```

After compiling run the program using this command ```java ConsoleCalculatorApp```

## Design Pattens 
In this project I used 5 different design patterns: Observer, Strategy, Flyweight, Factory and Builder.

## Behavioral Patterns

### Observer 
The Observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods. It is mainly used to implement distributed event handling systems, in "event driven" software.

In this project, the Observer pattern is used in the ```CalculatorContext```, ```CalculatorObserve``` and ```ConsoleObserver``` classes.

The ```CalculatorContext``` class acts as the subject. It maintains a reference to a ```CalculatorObserver``` object and calls its ```updateResult()``` method whenever it calculates a new result:

CalculatorContext.java:
```java
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
}
```
The ```CalculatorObserver``` interface defines the ```updateResult()``` method that observers implement. The ```ConsoleObserver``` class is an observer. It implements the ```updateResult()``` method to print the result to the console.

ConsoleObserver.java:

```Java
public class ConsoleObserver implements CalculatorObserver {
    @Override
    public void updateResult(double result) {
        System.out.println("The result is: " + result);
    }
}
```
In the ```ConsoleCalculatorApp``` class, a ```ConsoleObserver``` is created and passed to the ```CalculatorContext```. This sets up the observer pattern's relationship.

ConsoleCalculatorApp.java:

```Java
public class ConsoleCalculatorApp {
    public static void main(String[] args) {
        CalculatorObserver observer = new ConsoleObserver();
        CalculatorContext calculatorContext = new CalculatorContext(observer);
        /**...later code (removed for clearity)*/
    }
}
```
To sum up, here the Observer pattern in action is whenever ```CalculatorContext``` calculates a new result, it notifies ```ConsoleObserver``` which then prints the result.

### Strategy
The Strategy pattern is a behavioral design pattern that turns a set of behaviors into objects and makes them interchangeable inside original context object. The original object, called context, holds a reference to a strategy object and delegates it executing the behavior. In order to change the way the context performs its work, other objects may replace the currently linked strategy object with another one.

In this project the Strategy pattern is used in the ```CalculatorContext``` class and the Operation interface. The ```CalculatorContext``` class, which acts as the context, maintains a reference to an Operation object, which represents the strategy. The Operation interface defines a method ```execute()``` that takes two numbers and returns a result. This method is implemented by the ```AddOperation```, ```SubtractOperation```, ```MultiplyOperation```, and ```DivideOperation``` classes, each providing a different strategy for performing an operation.

Here's how the ```CalculatorContext``` class uses the ```Operation``` strategy:

CalculatorContext.java:

```Java
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
}
```
The ```Operation``` interface and its implementations are defined in ```CalculatorOperation```.

CalculatorOperation.java:
```Java
interface Operation {
    double execute(double a, double b);
}

class AddOperation implements Operation {
    /*code not present for clearity*/
}
class SubtractOperation implements Operation {
    /*code not present for clearity*/
}
class MultiplyOperation implements Operation {
    /*code not present for clearity*/
}
class DivideOperation implements Operation {
    /*code not present for clearity*/
}
```
The ```OperationFactory``` class is used to create and manage Operation objects.

OperationFactory.java:
```Java
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
```
To sum up, the Strategy pattern in this project allows the ```CalculatorContext``` to perform different operations like addition by using different ```Operation``` strategies. The ```OperationFactory``` is used to create and manage these strategies.

## Structural Patterns

### Flyweight
The Flyweight pattern is a structural design pattern that aims to minimize memory use by sharing as much data as possible with similar objects. 

In this project, the Flyweight pattern is used in the ```OperationFactory``` class. In this class, instances of ```Operation``` subclasses (```AddOperation```, ```SubtractOperation```, ```MultiplyOperation```, ```DivideOperation```) are created once and stored in a HashMap. When an operation is requested the ```OperationFactory``` returns the existing instance from the map instead of creating a new one. This will save memory and improve performance by avoiding creating identical objects.

OperationFactory.java:
```Java
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
```
## Creational Patterns
### Factory Method
The Factory Method pattern is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

In this project, the Factory Method pattern is used in the ```OperationFactory``` class. There, the ```createOperation()``` method acts as the factory method. It takes a string ```operationType``` as input and returns an instance of the ```Operation``` subclass based on the ```operationType```. This allows the type of operation to be decided at runtime, which is a key feature of the Factory Method pattern.

OperationFactory.java:
```Java
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
```
### Builder
The Builder pattern is a creational design pattern that provides a flexible and readable way to create complex objects. It allows for the step-by-step construction of a complex object, where each step can be customized independently of the others.

In this App, the Builder pattern is used in the ```CalculatorConfiguration``` class. The ```CalculatorConfiguration.Builder``` (Builder Class inside the CalculatorConfiguration) class acts as the builder, providing methods to set the configuration parameters (allowNegativeResults and resultPrecision) and a ```build()``` method to create a ```CalculatorConfiguration``` object.

In the example below, the ```Builder``` class provides methods to set the ```allowNegativeResults``` and ```resultPrecision``` parameters. These methods return the ```Builder``` object itself, allowing the methods to be chained together. The ```build()``` method creates and returns a new ```CalculatorConfiguration``` object, using the parameters that were set on the ```Builder```.


CalculatorConfiguration.java:
```java
public class CalculatorConfiguration {
    private boolean allowNegativeResults;
    private int resultPrecision;

    private CalculatorConfiguration(Builder builder) {
        this.allowNegativeResults = builder.allowNegativeResults;
        this.resultPrecision = builder.resultPrecision;
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
```
In the main java file ```ConsoleCalculatorApp``` a new ```CalculatorConfiguration``` object is created with ```allowNegativeResults``` set to variable ```allowNegativeResults``` and ```resultPrecision``` set to ```resultPrecision``` variable, the user sets the configuration of the calculator with the first inputs. This object configures the behavior of the calculator.

ConsoleCalculatorApp.java:
```Java
// Set calc config
CalculatorConfiguration config = new CalculatorConfiguration.Builder()
    .resultPrecision(resultPrecision)
    .allowNegativeResults(allowNegativeResults)
    .build();

calculatorContext.setConfiguration(config);

OperationFactory operationFactory = new OperationFactory(config);
```
The application is built with the configuration options chosen by the user. after the configuration is inputed by the user it sets it in a few classes, one example is the ```CalculatorConfiguration``` class.

CalculatorConfiguration.java:
```java
 private CalculatorConfiguration configuration;

    public void setConfiguration(CalculatorConfiguration configuration) {
        this.configuration = configuration;
    }
```
## Sources Used
- [Refactoring Guru](https://refactoring.guru/design-patterns)

## Authors
- [Tomas Tomkevicius](https://www.github.com/Tomasx420)