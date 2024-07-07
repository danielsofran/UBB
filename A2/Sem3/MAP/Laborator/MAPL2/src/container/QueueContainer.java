package container;

import model.Task;

public class QueueContainer extends AbstractContainer {

    @Override
    protected Task removeElement() {
        Task rez = tasks[0];
        for(int i=0;i<size-1;++i)
            tasks[i] = tasks[i+1];
        size--;
        return rez;
    }

    @Override
    protected void addElement(Task task) {
        for(int i=size-1; i>=0; --i)
            tasks[i+1] = tasks[i];
        size++;
        tasks[0] = task;
    }
}
