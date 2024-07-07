package mock1.business.service;

import mock1.business.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class PathService {
    @Autowired
    private PathUtils pathUtils;
    @Value("${counties_count}")
    private Integer countyCount;
    private Integer currentCountyIndex = 0;

    public synchronized Path getCurrentFile() {
        if (currentCountyIndex < countyCount) {
            Path path = pathUtils.getInputCountyPath(currentCountyIndex);
            currentCountyIndex ++;
            return path;
        }
        else {
            return null;
        }
    }
}
