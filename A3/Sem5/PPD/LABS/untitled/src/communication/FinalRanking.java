package communication;

import dataTypes.ContestantData;
import dataTypes.CountryData;

import java.io.Serializable;
import java.util.List;

public class FinalRanking implements Serializable {
    private String contestantRanking;
    private String countryRanking;

    public FinalRanking(List<ContestantData> contestantData, List<CountryData> countryData) {
        StringBuilder contestantRanking = new StringBuilder();
        StringBuilder countryRanking = new StringBuilder();
        for (ContestantData contestantDatum : contestantData) {
            contestantRanking.append(contestantDatum.getCountryId()).append(" ")
                    .append(contestantDatum.getId()).append(" ")
                    .append(contestantDatum.getScore()).append("\n");
        }
        for (CountryData countryDatum : countryData) {
            countryRanking.append(countryDatum.getId()).append(" ")
                    .append(countryDatum.getScore()).append("\n");
        }
        this.contestantRanking = contestantRanking.toString();
        this.countryRanking = countryRanking.toString();
    }

    public String getContestantRanking() {
        return contestantRanking;
    }

    public void setContestantRanking(String contestantRanking) {
        this.contestantRanking = contestantRanking;
    }

    public String getCountryRanking() {
        return countryRanking;
    }

    public void setCountryRanking(String countryRanking) {
        this.countryRanking = countryRanking;
    }
}
