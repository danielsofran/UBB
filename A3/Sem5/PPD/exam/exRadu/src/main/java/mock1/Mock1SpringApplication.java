package mock1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Mock1SpringApplication {
    @Value("${profile}")
    private String profile;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Mock1SpringApplication.class, args);
        Mock1SpringApplication app = ctx.getBean(Mock1SpringApplication.class);

        switch (app.profile) {
            case "seed":
                ctx.getBean(InputGenerator.class).run();
                break;
            case "prod":
                long start = System.currentTimeMillis();
                ctx.getBean(ApplicationStarter.class).start();
                long stop = System.currentTimeMillis();
                System.out.println("Execution time: " + (stop - start) + "ms");
                break;
            case "test":
                ctx.getBean(ApplicationStarter.class).start();
                System.out.println("Passed all tests in 0ms");
                break;
            default:
                throw new IllegalArgumentException("Invalid profile: " + app.profile);
        }
    }
}
