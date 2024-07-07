package container;

import model.Task;

public class NumericBubbleSorter extends AbstractSorter<Integer> {
    @Override
    public void sort(Integer[] vector) {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            for(int i=0;i<vector.length-1;++i)
                if(vector[i] > vector[i+1])
                {
                    Integer aux = vector[i];
                    vector[i] = vector[i+1];
                    vector[i+1] = aux;
                    sorted = false;
                }
        }
    }
}
