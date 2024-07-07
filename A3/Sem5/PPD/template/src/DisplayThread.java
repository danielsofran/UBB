import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class DisplayThread implements Runnable {
    FileWriter fileWriter = new FileWriter("output.txt");
    LocalTime start = LocalTime.now();

    public DisplayThread() throws IOException {
    }

    @Override
    public void run() {
        while(Main.shouldRun.get()) {
            // get the time passed in ms
            long timePassed = LocalTime.now().toNanoOfDay() - start.toNanoOfDay();
            // convert to miliseconds
            timePassed = timePassed / 1000000;
            if (timePassed > Main.Dt) {
                System.out.println("Timpul a expirat");
                Main.shouldRun.set(false);
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            // get the count of in astepare, in procesare, finalizate
            int inAsteptare = 0;
            int inProcesare = 0;
            int finalizate = 0;
            for (Comanda comanda : Main.bucatarieComenziPizza) {
                if (comanda.getStatus().equals("in asteptare")) {
                    inAsteptare++;
                } else if (comanda.getStatus().equals("in procesare")) {
                    inProcesare++;
                } else if (comanda.getStatus().equals("finalizata")) {
                    finalizate++;
                }
            }
            for (Comanda comanda : Main.bucatarieComenziPaste) {
                if (comanda.getStatus().equals("in asteptare")) {
                    inAsteptare++;
                } else if (comanda.getStatus().equals("in procesare")) {
                    inProcesare++;
                } else if (comanda.getStatus().equals("finalizata")) {
                    finalizate++;
                }
            }
            for (Comanda comanda : Main.bucatarieComenziSushi) {
                if (comanda.getStatus().equals("in asteptare")) {
                    inAsteptare++;
                } else if (comanda.getStatus().equals("in procesare")) {
                    inProcesare++;
                } else if (comanda.getStatus().equals("finalizata")) {
                    finalizate++;
                }
            }

            System.out.println("Comenzi in asteptare: " + inAsteptare);
            System.out.println("Comenzi in procesare: " + inProcesare);
            System.out.println("Comenzi finalizate: " + finalizate);

            try {
                fileWriter.write("Comenzi in asteptare: " + inAsteptare + "\n");
                fileWriter.write("Comenzi in procesare: " + inProcesare + "\n");
                fileWriter.write("Comenzi finalizate: " + finalizate + "\n");
            }
            catch (IOException e){
                e.printStackTrace();
            }

            try {
                Thread.sleep(Main.Rn);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
