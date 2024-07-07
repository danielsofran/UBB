import java.util.HashSet;
import java.util.Set;

public class Main {
    static int n = 100000000;
    static int nrThreads = 4;
    static int[] a = new int[n];
    static int[] b = new int[n];
    static int[] c = new int[n];

    static void operation(int i) {
        c[i] = a[i] + b[i];
//        c[i] = (int)Math.pow(a[i], 3) + (int)Math.pow(b[i], 3);
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i;
        }
    }

    static void verify(){
        for (int i = 0; i < n; i++) {
            if (c[i] != 2 * i) {
                System.out.println("Wrong answer at index " + i + " -> " + c[i]);
                throw new RuntimeException();
            }
        }
        System.out.println("Correct answer");
    }

    static void estimate(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + " ms");
    }

    static void secvential() {
        init();
        for (int i = 0; i < n; i++) {
            operation(i);
        }
    }

    public static void main(String[] args) {
        estimate(Main::linear);
        verify();
    }

    class LinearThread implements Runnable {
        int start;
        int end;

        public LinearThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                operation(i);
            }
        }
    }

    static void linear() {
        init();
        int segment = n / nrThreads;
        Thread[] threads = new Thread[nrThreads];
        for(int i = 0; i < nrThreads; i++) {
            int start = i * segment;
            int end = Math.min((i + 1) * segment, n);
            threads[i] = new Thread(new Main().new LinearThread(start, end));
        }
        for (int i = 0; i < nrThreads; i++) {
            threads[i].start();
        }
        for (int i = 0; i < nrThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + i + " interrupted");
                e.printStackTrace();
            }
        }
    }
    
    class CircularThread implements Runnable {
        int index, increment;
        
        public CircularThread(int index, int increment) {
            this.index = index;
            this.increment = increment;
        }
        
        public void run() {
            for (int i = index; i < n; i += increment) {
                operation(i);
            }
        }
    }

    static void circular() {
        init();
        Thread[] threads = new Thread[nrThreads];
        for(int i = 0; i < nrThreads; i++) {
            threads[i] = new Thread(new Main().new CircularThread(i, nrThreads));
        }
        for (int i = 0; i < nrThreads; i++) {
            threads[i].start();
        }
        for (int i = 0; i < nrThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + i + " interrupted");
                e.printStackTrace();
            }
        }
    }
}
