package mock1.model.entity.ranking;

import lombok.Getter;
import mock1.model.entity.score.PartialScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class RankingsWrapper {
    @Value("${problems_count}")
    private Integer problemsCount;

    @Getter
    private GenericRanking fullRanking;
    private List<GenericRanking> problemsRankingList;

    @PostConstruct
    public void postConstruct() {
        this.fullRanking = new GenericRanking();
        this.problemsRankingList = new ArrayList<>();

        for (int i = 0; i < problemsCount; i++) {
            this.problemsRankingList.add(new GenericRanking());
        }
    }

    public void addScore(PartialScore partialScore) {
        fullRanking.addScore(partialScore);
        problemsRankingList.get(partialScore.getProblemId()).addScore(partialScore);
    }

    public GenericRanking getProblemRanking(Integer problemId) {
        return problemsRankingList.get(problemId);
    }
}
