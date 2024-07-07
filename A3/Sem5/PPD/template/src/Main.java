import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    // debug
    public static AtomicInteger generatedCommands = new AtomicInteger(0);
    public static AtomicInteger processedCommands = new AtomicInteger(0);

    public final static int C = 5;
    public final static int Yc = 10;
    public final static int Tc = 100; // 100 - 200
    public final static int B = 2;
    public final static int Xb = 100; // 100 - 200
    public final static int Rn = 200;
    public final static int Dt = 7000;

//    public static Queue<Conamda> preluareComenziSushi = new LinkedBlockingQueue<>();
//    public static Queue<Conamda> preluareComenziPizza = new LinkedBlockingQueue<>();
//    public static Queue<Conamda> preluareComenziPaste = new LinkedBlockingQueue<>();
    public static Queue<Comanda> preluareComenzi = new LinkedBlockingQueue<>();

    public static Queue<Comanda> bucatarieComenziSushi = new LinkedBlockingQueue<>();
    public static Queue<Comanda> bucatarieComenziPizza = new LinkedBlockingQueue<>();
    public static Queue<Comanda> bucatarieComenziPaste = new LinkedBlockingQueue<>();

    static Thread[] generators = new Thread[C];
    static Thread[] bucatari = new Thread[B];
    static Thread echipaSushi = new Thread(new EchipaSushiThread(new ReentrantLock()));
    static Thread echipaPizza = new Thread(new EchipaPizzaThread(new ReentrantLock()));
    static Thread echipaPaste = new Thread(new EchipaPasteThread(new ReentrantLock()));
    static Thread display;

    static {
        try {
            display = new Thread(new DisplayThread());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static AtomicBoolean shouldRun = new AtomicBoolean(true);

    public static void main(String[] args) throws IOException {
        // creating
        for(int i = 0; i < C; i++) {
            generators[i] = new Thread(new GenerateThread(i*100));
            generators[i].start();
        }
        for(int i = 0; i < B; i++) {
            bucatari[i] = new Thread(new BThread());
            bucatari[i].start();
        }
        echipaSushi.start();
        echipaPizza.start();
        echipaPaste.start();
        display.start();

        // joining
        for(int i = 0; i < C; i++) {
            try {
                generators[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < B; i++) {
            try {
                bucatari[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            echipaSushi.join();
            echipaPizza.join();
            echipaPaste.join();
            display.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        // afisare coada
//        System.out.println("Coada de preluare comenzi:");
//        for (Conamda comanda : preluareComenzi) {
//            System.out.println("Comanda " + comanda.getId_comanda() + " de tip " + comanda.getTip_mancare() + " cu status " + comanda.getStatus());
//        }
    }
}