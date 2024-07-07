public class BThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            if (!Main.preluareComenzi.isEmpty()) {
                Comanda comanda = Main.preluareComenzi.poll();
                if(comanda == null) {
                    continue;
                }
                comanda.setStatus("in asteptare");
                if (comanda.getTip_mancare().equals("sushi")) {
                    Main.bucatarieComenziSushi.add(comanda);
                } else if (comanda.getTip_mancare().equals("pizza")) {
                    Main.bucatarieComenziPizza.add(comanda);
                } else if (comanda.getTip_mancare().equals("paste")) {
                    Main.bucatarieComenziPaste.add(comanda);
                }
                Main.processedCommands.incrementAndGet();
            }
            else if (Main.processedCommands.get() == Main.Yc) {
                break;
            }
//            else {
//                try {
//                    Main.preluareComenzi.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
