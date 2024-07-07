import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class FileUtils {
    static List<String> read(String filename) throws IOException {
        filename = "input/" + filename;
        File file = new File(filename);
        Path path = Paths.get(file.getPath());
        return Files.readAllLines(path);
    }

    static void write(String filename, String content) throws IOException {
        String filepath = filename;//"input/" + filename;
        File file = new File(filepath);
        Path path = Paths.get(file.getPath());
        Files.writeString(path, content);
    }
}