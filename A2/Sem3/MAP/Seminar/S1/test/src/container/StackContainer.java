package container;

import model.Task;

public class StackContainer extends AbstractContainer {

    @Override
    protected Task removeElement() {
        return tasks[--size];
    }

    @Override
    protected void addElement(Task task) {
        tasks[size++] = task;
    }
}
