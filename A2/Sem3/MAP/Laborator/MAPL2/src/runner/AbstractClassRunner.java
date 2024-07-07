package runner;

import model.Task;

public abstract class AbstractClassRunner implements TaskRunner {
    protected TaskRunner runner;

    public AbstractClassRunner(TaskRunner runner){
        this.runner = runner;
    }

    @Override
    abstract public void executeOneTask();

    @Override
    public void executeAll() {
        while (runner.hasTask())
            executeOneTask();
    }

    @Override
    public void addTask(Task t) {
        runner.addTask(t);
    }

    @Override
    public boolean hasTask() {
        return runner.hasTask();
    }
}
