package model;

public interface NumericOperations<T> {
    T Add(T other);
    T Substract(T other);
    T Multiply(T other);
    T Divide(T other);
    void parse(String s);
}
