package mock1.model.entity.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Problem {
    private Integer id;
    private Integer startTimestamp;
    private Integer deadlineTimestamp;
}
