package mock1.model.entity.ranking;

import mock1.model.entity.score.PartialScore;
import mock1.model.entity.score.ParticipantScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GenericRanking {
    private final Map<Integer, ParticipantScore> scores;

    public GenericRanking() {
        scores = new HashMap<>();
    }

    public synchronized void addScore(PartialScore partialScore) {
        ParticipantScore participantScore = scores.computeIfAbsent(partialScore.getParticipantId(),
                k -> new ParticipantScore(partialScore.getParticipantId(), 0, 0));

        if(partialScore.isInTime()) {
            participantScore.addScore(partialScore.getScore());
        }
        participantScore.addDelay(partialScore.getUsedTime());
    }

    @Override
    public synchronized String toString() {
        return scores.values()
                .stream()
                .sorted()
                .map(ParticipantScore::toString)
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
