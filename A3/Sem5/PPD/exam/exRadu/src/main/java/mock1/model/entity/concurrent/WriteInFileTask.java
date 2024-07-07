package mock1.model.entity.concurrent;

import lombok.AllArgsConstructor;
import mock1.business.service.StatusService;
import mock1.model.entity.ranking.GenericRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class WriteInFileTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(StatusService.class);

    private GenericRanking ranking;
    private Path path;

    @Override
    public void run() {
        log.info("Writing in file: " + path);
        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            log.info("Opened writer for file: " + path);
            String content = ranking.toString();
            log.info("Writing content: " + content.split("\n").length + " lines");
            writer.write(content);
            log.info("Finished file: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
