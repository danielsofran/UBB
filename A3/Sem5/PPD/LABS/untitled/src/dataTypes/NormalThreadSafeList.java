package dataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalThreadSafeList<T> {
    private final List<T> data = new ArrayList<>();
    private boolean isWriting = false;

    public synchronized void insert(T value) throws InterruptedException {
        while (isWriting) {
            wait();
        }
        isWriting = true;
        if (!data.contains(value)) {
            data.add(value);
        }
        isWriting = false;
        notify();  // Notify any waiting threads that an insertion has occurred
    }

    public synchronized boolean contains(T value) throws InterruptedException {
        while (isWriting) {
            wait();
        }
        isWriting = true;
        boolean contains = data.contains(value);
        isWriting = false;
        notify();
        return contains;
    }

    public synchronized List<T> getData() {
        return data;
    }

    public synchronized void delete(T value) throws InterruptedException {
        while (isWriting) {
            wait();
        }
        isWriting = true;
        data.remove(value);
        isWriting = false;
        notify();
    }

}
