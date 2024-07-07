package model;

public class BinaryExpression<T extends NumericOperations<T>> implements Expression<T> {
    private final T first;
    private final T second;
    private final Operation operation;

    public BinaryExpression(T first, T second, Operation operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return first + " " + operation + " " + second;
    }

    @Override
    public T value() {
        switch (operation.getSign()){
            case '+': return first.Add(second);
            case '-': return first.Substract(second);
            case '*': return first.Multiply(second);
            case '/': return first.Divide(second);
        }
        return null;
    }
}
