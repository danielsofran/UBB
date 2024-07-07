package model;

public class UnaryOperation extends Operation{
    public UnaryOperation(char sign, int priority) {
        super(sign, priority, 1);
    }
}
