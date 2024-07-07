package mock1.model.entity.concurrent;

import lombok.AllArgsConstructor;
import mock1.model.entity.ranking.RankingsWrapper;
import mock1.model.entity.score.PartialScore;

@AllArgsConstructor
public class WorkTask implements Runnable {
    private final RankingsWrapper rankingsWrapper;
    private final PartialScore partialScore;

    @Override
    public void run() {
        rankingsWrapper.addScore(partialScore);
    }
}
