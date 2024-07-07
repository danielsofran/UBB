package model;

public class BinaryOperation extends Operation{
    public BinaryOperation(char sign, int priority) {
        super(sign, priority, 2);
    }
}
