package mock1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PartialScoreBlockDto {
    private List<PartialScoreDto> partialScores;
}
