import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
    static List<String> read(Integer variant) throws IOException {
        String filename = "input/input" + variant + ".cpp";
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        return Files.readAllLines(path);
    }

    static void write(String filename, String content) throws IOException {
        String filepath = "output/" + filename;
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        Files.writeString(path, content);
    }
}
