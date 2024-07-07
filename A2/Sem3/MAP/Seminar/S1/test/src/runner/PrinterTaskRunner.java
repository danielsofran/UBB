package runner;

import utils.Constants;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractClassRunner {

    @Override
    public void executeOneTask(){
        this.runner.executeOneTask();
        this.decoratorExecuteOneTask();
    }

    public PrinterTaskRunner(TaskRunner runner) {
        super(runner);
    }

    public void decoratorExecuteOneTask() {
        System.out.println("Task executat la: " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
    }
}
