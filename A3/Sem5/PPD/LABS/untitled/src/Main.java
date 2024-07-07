import java.io.IOException;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
//        Server server = new Server(4, 4, 1, 1000);
        long start = System.currentTimeMillis();
        Server server = new Server(4, 4, 1, 10);
        server.run();
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
//        try {
//            threaded.processInput(2, 16);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}