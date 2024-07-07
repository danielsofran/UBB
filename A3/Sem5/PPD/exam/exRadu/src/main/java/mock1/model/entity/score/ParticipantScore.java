package mock1.model.entity.score;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ParticipantScore implements Comparable<ParticipantScore> {
    private Integer participantId;
    private Integer score;
    private Integer totalDelay;

    public void addScore(Integer score) {
        this.score += score;
    }

    public void addDelay(Integer delay) {
        this.totalDelay += delay;
    }

    @Override
    public String toString() {
        return participantId +
                "," + score +
                "," + totalDelay +
                '\n';
    }

    @Override
    public int compareTo(ParticipantScore other) {
        int scoreCompareResult = - this.score.compareTo(other.getScore());
        if(scoreCompareResult == 0) {
            return this.totalDelay.compareTo(other.getTotalDelay());
        }
        else {
            return scoreCompareResult;
        }
    }
}
