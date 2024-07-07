package mock1.business.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PathUtils {
    private static final Logger log = LoggerFactory.getLogger(PathUtils.class);

    @Value("${input_directory_path:src/main/resources/input}")
    private String inputDirPath;
    @Value("${output_directory_path:src/main/resources/output}")
    private String outputDirPath;

    public Path getInputMetadataPath() {
        Path path = Paths.get(inputDirPath, "metadata.csv");
        ensurePathExistsAsFile(path);
        return path;
    }

    public Path getInputCountyPath(Integer currentCountyIndex) {
        Path path = Paths.get(inputDirPath, "county" + currentCountyIndex + ".csv");
        ensurePathExistsAsFile(path);
        return path;
    }

    public Path getOutputProblemRankingPath(Integer problemId) {
        Path path = Paths.get(outputDirPath, "problem_" + problemId + ".csv");
        ensurePathExistsAsFile(path);
        return path;
    }

    public Path getOutputFullRankingPath() {
        Path path = Paths.get(outputDirPath, "ranking.csv");
        ensurePathExistsAsFile(path);
        return path;
    }

    private void ensurePathExistsAsFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if(Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            log.error("Error creating file!");
            throw new RuntimeException(e);
        }
    }
}
