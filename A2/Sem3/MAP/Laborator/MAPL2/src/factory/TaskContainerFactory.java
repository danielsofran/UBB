package factory;

import container.Container;
import container.QueueContainer;
import container.StackContainer;
import container.Strategy;

import java.util.Objects;

public class TaskContainerFactory implements ContainerFactory {
    private TaskContainerFactory(){}

    private static TaskContainerFactory instance = new TaskContainerFactory();

    public static TaskContainerFactory getInstance(){
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        if(strategy == Strategy.LIFO)
            return new StackContainer();
        if(strategy == Strategy.FIFO)
            return new QueueContainer();
        return null;
    }
}
