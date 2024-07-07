package container;

import model.Task;
import utils.Constants;

public class VectorContainer implements RandomAccessContainer{
    Task[] elems;
    int size;
    private static final float factor = 1.5f;

    public VectorContainer(){
        elems = new Task[Constants.INITIAL_TASK_SIZE];
        size = 0;
    }

    private boolean isIndexValid(int index){
        return 0 >= index && index<size;
    }

    private void resize(){
        Task[] newTasks = new Task[2*size];
        System.arraycopy(elems, 0, newTasks, 0, size);
        elems = newTasks;
    }

    @Override
    public Task remove() {
        return elems[--size];
    }

    @Override
    public void add(Task task) {
        if(size == elems.length) resize();
        elems[size++] = task;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public Task elementAt(int index) throws IndexOutOfBoundsException {
        if(!isIndexValid(index)) throw new IndexOutOfBoundsException();
        return elems[index];
    }

    @Override
    public Task remove(int index) throws IndexOutOfBoundsException {
        if(!isIndexValid(index)) throw new IndexOutOfBoundsException();
        Task rez = elems[index];
        for(int i=index;i<size-1;++i)
            elems[i] = elems[i+1];
        size--;
        return rez;
    }

    @Override
    public void add(Task task, int index) throws IndexOutOfBoundsException {
        if(!isIndexValid(index)) throw new IndexOutOfBoundsException();
        if(size == elems.length) resize();

        for (int i=size-1;i>=index;--i)
            elems[i+1] = elems[i];
        elems[index] = task;
    }
}
