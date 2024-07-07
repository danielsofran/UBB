package dataTypes;

import java.io.Serializable;
import java.util.Objects;

public class CountryData implements Comparable<CountryData>, Serializable {
    private int id;
    private int score;

    public CountryData(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public CountryData(int id, int countryId, int score) {
        this.id = id;
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

    @Override
    public int compareTo(CountryData o) {
        return o.score - this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryData that = (CountryData) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addScore(int score) {
        this.score += score;
    }
}
