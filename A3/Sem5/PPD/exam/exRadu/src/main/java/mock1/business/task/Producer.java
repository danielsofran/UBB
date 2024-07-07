package mock1.business.task;


import mock1.business.mapper.ScoreMapper;
import mock1.business.service.PathService;
import mock1.business.service.StatusService;
import mock1.model.dto.PartialScoreBlockDto;
import mock1.model.entity.concurrent.WorkPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Scope("prototype")
public class Producer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Value("${block_size}")
    private int BLOCK_SIZE;

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private PathService pathService;
    @Autowired
    private WorkPool workPool;
    @Autowired
    private StatusService statusService;

    @Override
    public void run() {
        log.info("Producer started");
        for(Path currentFile = pathService.getCurrentFile(); currentFile != null; currentFile = pathService.getCurrentFile()) {
            processFile(currentFile);
        }
        statusService.onProducerFinished();
    }

    private void processFile(Path currentFile) {
        try(Scanner scanner = new Scanner(currentFile)) {
            List<String> block = new ArrayList<>();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                block.add(line);

                if(block.size() >= BLOCK_SIZE) {
                    PartialScoreBlockDto blockDto = scoreMapper.toBlockDto(block);
                    List<Runnable> runnables = scoreMapper.toRunnable(blockDto);
                    runnables.forEach(runnable -> workPool.execute(runnable));
                    block.clear();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
