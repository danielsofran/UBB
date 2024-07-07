import java.util.concurrent.locks.Lock;

public class EchipaPasteThread implements Runnable{
    Lock lock;

    public EchipaPasteThread(Lock lock){
        this.lock = lock;
    }

    Comanda getComandaInAsteptare() {
        for (Comanda comanda : Main.bucatarieComenziPaste) {
            if (comanda.getStatus().equals("in asteptare")) {
                return comanda;
            }
        }
        return null;
    }

    @Override
    public void run(){
        boolean shouldRun = true;
        while (shouldRun && Main.processedCommands.get() < Main.Yc){
            while (!Main.bucatarieComenziPaste.isEmpty()){
                // change status to "in procesare"
                lock.lock();
                Comanda comanda = getComandaInAsteptare();
                if(comanda == null) {
                    shouldRun = false;
                    break;
                }
                comanda.setStatus("in procesare");
                Main.bucatarieComenziPaste.add(comanda);
                try{
                    Thread.sleep(Main.Xb);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                Main.bucatarieComenziPaste.poll();
                comanda.setStatus("finalizata");
                Main.bucatarieComenziPaste.add(comanda);
                lock.unlock();
            }
        }
    }
}
