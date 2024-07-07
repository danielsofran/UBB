import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Utils {
    static List<String> read(String filename) throws IOException {
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        return Files.readAllLines(path);
    }

    public static void write(String filename, String content) throws IOException {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }
}
