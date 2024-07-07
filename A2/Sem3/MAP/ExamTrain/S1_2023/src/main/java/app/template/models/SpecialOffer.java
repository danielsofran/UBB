package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.FK;
import app.template.orm.annotations.PK;

import java.time.LocalDate;

@DBEntity
public class SpecialOffer {
    @PK
    double specialOfferId;
    @FK(Table = Hotel.class, RefCol = "hotelId")
    double hotelId;
    LocalDate startDate;
    LocalDate endDate;
    int percents;

    public SpecialOffer(){}

    public SpecialOffer(double specialOfferId, double hotelId, LocalDate startDate, LocalDate endDate, int percents) {
        this.specialOfferId = specialOfferId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    public double getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(double specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    public double getHotelId() {
        return hotelId;
    }

    public void setHotelId(double hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialOffer)) return false;

        SpecialOffer that = (SpecialOffer) o;

        return Double.compare(that.specialOfferId, specialOfferId) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(specialOfferId);
        return (int) (temp ^ (temp >>> 32));
    }
}
