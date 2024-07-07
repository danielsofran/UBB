package mock1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartialScoreDto {
    private Integer participantId;
    private Integer problemId;
    private Integer score;
    private Integer submissionTimestamp;
}
