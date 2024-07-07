import java.util.concurrent.locks.Lock;

public class EchipaSushiThread implements Runnable{
    Lock lock;

    public EchipaSushiThread(Lock lock){
        this.lock = lock;
    }

    Comanda getComandaInAsteptare() {
        for (Comanda comanda : Main.bucatarieComenziSushi) {
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
            while (!Main.bucatarieComenziSushi.isEmpty()){
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
                Comanda comandaFinalizata = new Comanda(comanda.getId_comanda(), comanda.getTip_mancare(), comanda.getStatus());
                lock.lock();
                Main.bucatarieComenziSushi.remove(comanda);
                Main.bucatarieComenziSushi.add(comandaFinalizata);
                lock.unlock();
            }
        }
    }
}
