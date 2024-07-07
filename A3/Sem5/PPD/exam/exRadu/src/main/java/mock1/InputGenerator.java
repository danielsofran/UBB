package mock1;

import mock1.business.util.PathUtils;
import mock1.model.entity.problem.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class InputGenerator implements Runnable {
    @Value("${counties_count}")
    private int countyCount;
    @Value("${problems_count}")
    private int problemsCount;
    @Value("${county_participants_count}")
    private int countyParticipantsCount;

    @Autowired
    private PathUtils pathUtils;

    public void run() {

        List<Problem> problems = readProblems(pathUtils.getInputMetadataPath());

        for(int i = 0; i < countyCount; i++) {
            try (BufferedWriter writer = Files.newBufferedWriter(pathUtils.getInputCountyPath(i))) {
                populateCountyFile(i * countyParticipantsCount, writer, problems);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Problem> readProblems(Path path) {
        List<Problem> problems = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(path)) {
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

        return problems;
    }

    private void populateCountyFile(Integer startIndex, BufferedWriter writer,
                                           List<Problem> problems) throws IOException {
        for (int i = 0; i < problemsCount; i++) {
            for (int j = 0; j < countyParticipantsCount; j++) {
                int participantId = startIndex + j;
                int score = (int) (10 + Math.random() * 91);
                int delta = (problems.get(problemsCount - 1).getDeadlineTimestamp() -
                        problems.get(0).getStartTimestamp())
                        / (problemsCount * 10);
                int timeWindow = delta + problems.get(i).getDeadlineTimestamp() -
                        problems.get(i).getStartTimestamp();
                int submissionTime = (int) (problems.get(i).getStartTimestamp() +
                        Math.random() * timeWindow);

                String line = String.format("%d,%d,%d,%d\n", participantId, i, score, submissionTime);
                writer.write(line);
            }
        }
    }
}
