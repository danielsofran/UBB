import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Static {
    static int tari = 5, probleme = 10;
    static List<Rezultat> rezultate = new LinkedList<>();

    static String getFile(int tara, int problema) {
        return "RezultateT" + tara + "_P" + problema + ".txt";
    }

    static void addRezultat(Rezultat rezultat) {
        int index = rezultate.indexOf(rezultat);
        if(index == -1) {
            // gaseste pozitia de inserare, ordinea e descrescatoare dupa punctaj
            int i = Collections.binarySearch(rezultate, rezultat);
            if(i < 0) i = -i - 1;
            rezultate.add(i, rezultat);
        }
        else {
            Rezultat r = rezultate.get(index);
            if(rezultat.getPunctaj() == -1) r.setPunctaj(-1);
            else r.setPunctaj(r.getPunctaj() + rezultat.getPunctaj());
            // ordine descrescatoare dupa punctaj
            rezultate.remove(index);
            int i = Collections.binarySearch(rezultate, r);
            if(i < 0) i = -i - 1;
            rezultate.add(i, r);
        }
    }

    public static void main(String[] args) throws IOException {
        for(int tara = 1; tara <= tari; tara++) {
            for (int problema = 1; problema <= probleme; problema++) {
                String filename = getFile(tara, problema);
                List<String> lines = FileUtils.read(filename);
                for(String line : lines) {
                    String[] tokens = line.split(" ");
                    String concurent = tokens[0];
                    int punctaj = Integer.parseInt(tokens[1]);
                    Rezultat rezultat = new Rezultat(concurent, punctaj);
                    addRezultat(rezultat);
                }
            }
        }
        String content = "";
        for(Rezultat rezultat : rezultate) {
            content += rezultat + "\n";
        }
        FileUtils.write("RezultateStatic.txt", content);
    }
}
