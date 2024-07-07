import analizor.AnalizorSintactic;
import analizor.Gramatica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    static List<String> read(String filename) throws IOException {
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        return Files.readAllLines(path);
    }

    static void program() throws IOException {
        List<String> lines = read("./tmp/gramaticap1.txt");
        Gramatica gramatica = new Gramatica(lines);
        AnalizorSintactic analizorSintactic = new AnalizorSintactic(gramatica);
        boolean acc = analizorSintactic.isAccepted("id+id");
        System.out.println("acc: " + acc);
        analizorSintactic.printBandaDeIesire();
    }


    public static void main(String[] args) throws IOException {
        program();
    }
}