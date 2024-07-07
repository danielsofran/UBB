import java.io.IOException;

public class Generator {


    public static void main(String[] args) {
        int tari = 5, probleme = 10;
        for (int tara = 1; tara <= tari; tara++) {
            int concurenti = 80 + (int) (Math.random() * 20);
            for (int problema = 1; problema <= probleme; problema++) {
                String filename = "RezultateT" + tara + "_P" + problema + ".txt";
                String content = "";
                for(int concurent = 1; concurent <= concurenti; concurent++) {
                    int punctaj = (int) (Math.random() * 100);
                    if(Math.random() < 0.05) punctaj = -1;
                    if(Math.random() < 0.05) punctaj = 0;
                    if(punctaj != 0) content += "T"+ tara +"C"+concurent + " " + punctaj + "\n";
                }
                try {
                    FileUtils.write(filename, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}