package model;

import java.lang.reflect.Array;
import java.util.*;

public class MultiExpression<T extends NumericOperations<T>> implements Expression<T> {
    private String[] tokens;
    private Stack<Object> S;

    private void proccessTokens(){
        Stack<Operation> operations = new Stack<>();
        Queue<String> EPost = new LinkedList<>();
        for(String token : tokens)
        {
            if(NrComplex.isComplexNumber(token)) EPost.add(token);
            else{
                // sda curs 11 pg 17
            }
        }
    }

    public MultiExpression(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public T value() {
        return null;
    }
}
