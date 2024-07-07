package container;

import java.lang.reflect.Array;

public class NumericMergeSorter extends AbstractSorter<Integer> {

    private void merge(Integer[] vector, int st1, int dr1, int st2, int dr2)
    {
        int st1_cpy = st1;
        Integer[] out = vector.clone();
        int st = 0;
        while (st1 < dr1 && st2 < dr2){
            if(vector[st1] < vector[st2]) out[st++] = vector[st1++];
            else out[st++] = vector[st2++];
        }
        while (st1 < dr1) out[st++] = vector[st1++];
        while (st2 < dr2) out[st++] = vector[st2++];
        System.arraycopy(out, 0, vector, st1_cpy, st);
    }

    private void mergesort(Integer[] vector, int st, int dr){
        if(Math.abs(dr-st)<2) return;
        int middle = (st+dr) / 2;
        mergesort(vector, st, middle);
        mergesort(vector, middle, dr);
        merge(vector, st, middle, middle, dr);
    }

    @Override
    public void sort(Integer[] vector) {
        mergesort(vector, 0, vector.length);
    }
}
