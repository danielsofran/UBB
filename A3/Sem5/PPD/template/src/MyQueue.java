import java.util.Queue;

public class MyQueue<T> {
    Queue<T> queue;

    public MyQueue(Queue<T> queue) {
        this.queue = queue;
    }

    public void adauga(T message) throws InterruptedException {
        while (queue.size() == 10) {
            wait();
        }
        queue.add(message);
        notifyAll();
    }

    public void adauga(T message, Runnable callback) throws InterruptedException {
        while (queue.size() == 10) {
            wait();
        }
        queue.add(message);
        notifyAll();
        callback.run();
    }

    public T preia() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T elem = queue.poll();
        notifyAll();
        return elem;
    }
}
