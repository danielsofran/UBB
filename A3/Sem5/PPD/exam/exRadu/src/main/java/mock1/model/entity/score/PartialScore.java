package mock1.model.entity.score;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartialScore {
    private Integer participantId;
    private Integer problemId;
    private Integer problemStartTimestamp;
    private Integer problemDeadlineTimestamp;
    private Integer score;
    private Integer submissionTimestamp;

    public boolean isInTime() {
        return problemStartTimestamp <= submissionTimestamp && submissionTimestamp <= problemDeadlineTimestamp;
    }

    public Integer getUsedTime() {
        return Math.min(submissionTimestamp, problemDeadlineTimestamp) - problemStartTimestamp;
    }
}
