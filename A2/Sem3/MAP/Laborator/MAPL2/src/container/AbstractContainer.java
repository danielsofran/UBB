package container;

import model.Task;

import static utils.Constants.INITIAL_TASK_SIZE;

public abstract class AbstractContainer implements Container {
    protected Task[] tasks;
    protected int size;

    public AbstractContainer() {
        size = 0;
        tasks = new Task[INITIAL_TASK_SIZE];
    }

    protected void resize() {
        Task[] newTasks = new Task[2*size];
        System.arraycopy(tasks, 0, newTasks, 0, size);
        tasks = newTasks;
    }

    protected abstract Task removeElement();
    protected abstract void addElement(Task task);

    @Override
    public Task remove(){
        if(isEmpty()) return null;
        return removeElement();
    }

    @Override
    public void add(Task task) {
        if(size == tasks.length) resize();
        addElement(task);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
