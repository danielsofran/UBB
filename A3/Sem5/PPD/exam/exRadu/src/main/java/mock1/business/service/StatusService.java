package mock1.business.service;

import mock1.business.util.PathUtils;
import mock1.model.entity.concurrent.WorkPool;
import mock1.model.entity.concurrent.WriteInFileTask;
import mock1.model.entity.ranking.RankingsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private static final Logger log = LoggerFactory.getLogger(StatusService.class);

    @Value("${producer_threads_count}")
    private Integer runningProducersCount;
    @Value("${problems_count}")
    private Integer problemsCount;
    @Autowired
    private WorkPool workPool;
    @Autowired
    private RankingsWrapper rankingsWrapper;
    @Autowired
    private PathUtils pathUtils;

    public synchronized void onProducerFinished() {
        runningProducersCount--;
        if(runningProducersCount == 0) {
            finish();
        }
    }

    private void finish() {
        log.info("All producers finished");
        workPool.execute(new WriteInFileTask(rankingsWrapper.getFullRanking(), pathUtils.getOutputFullRankingPath()));
        for(int i = 0; i < problemsCount; i++) {
            workPool.execute(new WriteInFileTask(rankingsWrapper.getProblemRanking(i), pathUtils.getOutputProblemRankingPath(i)));
        }

        workPool.finish();
    }
}
