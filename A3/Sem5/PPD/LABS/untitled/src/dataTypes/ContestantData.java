package dataTypes;

import java.io.Serializable;
import java.util.Objects;

public class ContestantData implements Comparable<ContestantData>, Serializable {
    private int id;
    private int countryId;
    private int score;

    public ContestantData(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public ContestantData(int id, int countryId, int score) {
        this.id = id;
        this.countryId = countryId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public int compareTo(ContestantData o) {
        return o.score - this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestantData that = (ContestantData) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
