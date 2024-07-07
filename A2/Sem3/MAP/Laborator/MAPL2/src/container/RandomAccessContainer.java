package container;

import model.Task;

public interface RandomAccessContainer extends Container{
    Task elementAt(int index) throws IndexOutOfBoundsException;
    Task remove(int index) throws IndexOutOfBoundsException;
    void add(Task task, int index) throws IndexOutOfBoundsException;
}
