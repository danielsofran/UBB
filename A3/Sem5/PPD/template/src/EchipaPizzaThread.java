import java.util.concurrent.locks.Lock;

public class EchipaPizzaThread implements Runnable{
    Lock lock;

    public EchipaPizzaThread(Lock lock){
        this.lock = lock;
    }

    Comanda getComandaInAsteptare() {
        for (Comanda comanda : Main.bucatarieComenziPizza) {
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
            while (!Main.bucatarieComenziPizza.isEmpty()){
                // change status to "in procesare"
                Comanda comanda = getComandaInAsteptare();
                if(comanda == null) {
                    shouldRun = false;
                    break;
                }
                comanda.setStatus("in procesare");
                try{
                    Thread.sleep(Main.Xb);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                comanda.setStatus("finalizata");
                Comanda newComanda = new Comanda(comanda.getId_comanda(), comanda.getTip_mancare(), comanda.getStatus());
                lock.lock();
                Main.bucatarieComenziPizza.remove(comanda);
                Main.bucatarieComenziPizza.add(newComanda);
                lock.unlock();
            }
        }
    }
}
