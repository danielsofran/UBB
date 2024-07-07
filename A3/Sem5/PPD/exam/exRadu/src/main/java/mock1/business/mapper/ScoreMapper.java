package mock1.business.mapper;

import mock1.business.util.PathUtils;
import mock1.model.dto.PartialScoreBlockDto;
import mock1.model.dto.PartialScoreDto;
import mock1.model.entity.concurrent.WorkTask;
import mock1.model.entity.problem.Problem;
import mock1.model.entity.ranking.RankingsWrapper;
import mock1.model.entity.score.PartialScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreMapper {
    @Autowired
    private PathUtils pathUtils;
    @Autowired
    private RankingsWrapper rankingsWrapper;

    private List<Problem> problems;

    @PostConstruct
    private void fetchProblems() {
        problems = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(pathUtils.getInputMetadataPath())) {
            reader.lines()
                    .map(line -> {
                        String[] parts = line.split(",");
                        return new Problem(
                                Integer.parseInt(parts[0]),
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                    })
                    .forEach(problems::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PartialScoreDto toDto(String str) {
        String[] parts = str.split(",");
        return new PartialScoreDto(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        );
    }

    public PartialScoreBlockDto toBlockDto(List<String> lines) {
        return new PartialScoreBlockDto(
                lines.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    private PartialScore toEntity(PartialScoreDto dto) {
        return new PartialScore(
                dto.getParticipantId(),
                dto.getProblemId(),
                problems.get(dto.getProblemId()).getStartTimestamp(),
                problems.get(dto.getProblemId()).getDeadlineTimestamp(),
                dto.getScore(),
                dto.getSubmissionTimestamp()
        );
    }

    public List<Runnable> toRunnable(PartialScoreBlockDto blockDto) {
        return blockDto.getPartialScores().stream()
                .map(dto -> new WorkTask(rankingsWrapper, toEntity(dto)))
                .collect(Collectors.toList());
    }
}
