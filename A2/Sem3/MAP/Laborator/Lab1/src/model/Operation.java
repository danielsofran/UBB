package model;

import java.util.Objects;

public class Operation {
    private char sign;

    public Operation(char sign, int priority, int nrOfOperators) {
        this.sign = sign;
        this.priority = priority;
        this.nrOfOperators = nrOfOperators;
    }

    private int priority;
    private int nrOfOperators;

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    protected int getNrOfOperators() {
        return nrOfOperators;
    }

    protected void setNrOfOperators(int nr_of_operators) {
        this.nrOfOperators = nr_of_operators;
    }

    @Override
    public String toString() {
        return ""+sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return getSign() == operation.getSign() && getPriority() == operation.getPriority() && getNrOfOperators() == operation.getNrOfOperators();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSign(), getPriority(), getNrOfOperators());
    }

    public static boolean isOperation(String sign){
        if(sign.length() != 1) return false;
        return "+-*/".indexOf(sign.charAt(0)) != -1;
    }
}
