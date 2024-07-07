package mock1.model.entity.concurrent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class WorkPool {
    @Value("${max_workload_queue_size}")
    private int MAX_Q_SIZE;
    private final LinkedList<Runnable> workQueue = new LinkedList<>();
    private boolean isFinished = false;

    public synchronized Runnable fetch() {
        while(workQueue.isEmpty() && !isFinished) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(workQueue.isEmpty()) {
            return null;
        }
        else {
            Runnable work = workQueue.removeFirst();
            notifyAll();
            return work;
        }
    }

    public synchronized void execute(Runnable work) {
        if(workQueue.size() >= MAX_Q_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        workQueue.addLast(work);
        notifyAll();
    }

    public synchronized void finish() {
        isFinished = true;
        notifyAll();
    }
}
