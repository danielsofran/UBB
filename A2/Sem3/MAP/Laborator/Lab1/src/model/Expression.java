package model;

public interface Expression<T extends NumericOperations<T>> {
    T value();
}
