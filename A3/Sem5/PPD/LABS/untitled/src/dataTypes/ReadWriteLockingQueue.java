package dataTypes;

import java.util.LinkedList;
import java.util.Queue;

public class ReadWriteLockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private boolean isWriteLocked = false;
    private boolean isReadLock = false;

    public synchronized void enqueue(T item) throws InterruptedException {
        while (isWriteLocked) {
            wait();
        }
        isWriteLocked = true;
        queue.add(item);
        isWriteLocked = false;
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (isWriteLocked || isReadLock) {
            wait();
        }
        isWriteLocked = true;
        isReadLock = true;
        T item = queue.poll();
        isReadLock = false;
        isWriteLocked = false;
        notifyAll();
        return item;
    }

    public synchronized boolean isEmpty() throws InterruptedException {
        while (isWriteLocked || isReadLock) {
            wait();
        }
        isWriteLocked = true;
        isReadLock = true;
        boolean isEmpty = queue.isEmpty();
        isReadLock = false;
        isWriteLocked = false;
        notifyAll();
        return isEmpty;
    }
}