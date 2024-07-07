public class GenerateThread implements Runnable{
    int startIndex;

    public GenerateThread(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        int nrCommandsToGenerate = Main.Yc;
        for (int i = 0; i < nrCommandsToGenerate; i++) {
            int finalIndex = startIndex + i;
            int random = (int) (Math.random() * 3);
            Comanda comanda = null;
            switch (random) {
                case 0:
                    comanda = new Comanda(finalIndex, "sushi", "preluata");
                    Main.preluareComenzi.add(comanda);
                    break;
                case 1:
                    comanda = new Comanda(finalIndex, "pizza", "preluata");
                    Main.preluareComenzi.add(comanda);
                    break;
                case 2:
                    comanda = new Comanda(finalIndex, "paste", "preluata");
                    Main.preluareComenzi.add(comanda);
                    break;
            }
            Main.generatedCommands.incrementAndGet();
            //System.out.println("Comanda " + comanda.getId_comanda() + " de tip " + comanda.getTip_mancare() + " a fost generata");
            try {
                Thread.sleep(Main.Tc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
