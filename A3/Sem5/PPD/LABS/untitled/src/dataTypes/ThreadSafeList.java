package dataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeList<T extends ContestantData> {
    private final List<T> data = new ArrayList<>();
    private boolean isWriting = false;

    public synchronized void insert(T value) throws InterruptedException {
        while (isWriting) {
            wait();
        }
        isWriting = true;
        if (!data.contains(value)) {
            int index = Collections.binarySearch(data, value);
            if (index < 0) {
                index = -index - 1;  // Adjust the index for insertion
            }
            data.add(index, value);
        }
        else {
            ContestantData contestantData = data.stream().filter(x -> x.getId() == value.getId()).findFirst().orElse(null);
            contestantData.setScore(contestantData.getScore() + value.getScore());
            data.sort(ContestantData::compareTo);
        }

        isWriting = false;
        notify();  // Notify any waiting threads that an insertion has occurred
    }

    public synchronized List<T> getData() {
        return data;
    }

}
