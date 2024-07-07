package mock1.business.task;

import mock1.model.entity.concurrent.WorkPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Consumer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private WorkPool workPool;

    @Override
    public void run() {
        log.info("Consumer started");
        for(Runnable runnable = workPool.fetch(); runnable != null; runnable = workPool.fetch()) {
//            log.info("Consumer processing task");
            runnable.run();
        }
    }
}
