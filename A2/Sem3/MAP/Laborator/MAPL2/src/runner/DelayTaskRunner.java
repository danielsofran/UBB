package runner;

import utils.Constants;

import java.time.LocalDateTime;

public class DelayTaskRunner extends AbstractClassRunner{
    @Override
    public void executeOneTask(){
        this.runner.executeOneTask();
        this.decoratorExecuteOneTask();
    }

    public DelayTaskRunner(TaskRunner runner) {
        super(runner);
    }

    public void decoratorExecuteOneTask(){
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
