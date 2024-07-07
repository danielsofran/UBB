package mock1;

import mock1.business.task.Consumer;
import mock1.business.task.Producer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStarter {
    @Value("${producer_threads_count}")
    private Integer producerCount;
    @Value("${consumer_threads_count}")
    private Integer consumerCount;

    @Autowired
    private ObjectProvider<Consumer> consumerProvider;
    @Autowired
    private ObjectProvider<Producer> producerProvider;

    public void start() {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < consumerCount; i++) {
            threads.add(new Thread(consumerProvider.getObject()));
        }
        for(int i = 0; i < producerCount; i++) {
            threads.add(new Thread(producerProvider.getObject()));
        }

        threads.forEach(Thread::start);

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
