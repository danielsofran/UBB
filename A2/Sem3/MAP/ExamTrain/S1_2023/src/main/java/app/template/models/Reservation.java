package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.FK;
import app.template.orm.annotations.PK;

import java.time.LocalDateTime;

@DBEntity
public class Reservation {
    @PK
    double reservationId;
    @FK(Table = Client.class, RefCol = "clientId")
    Long clientId;
    @FK(Table = Hotel.class, RefCol = "hotelId")
    double hotelId;
    LocalDateTime startDate;
    int noNights;

    public Reservation(){}

    public Reservation(double reservationId, Long clientId, double hotelId, LocalDateTime startDate, int noNights) {
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public double getReservationId() {
        return reservationId;
    }

    public void setReservationId(double reservationId) {
        this.reservationId = reservationId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getHotelId() {
        return hotelId;
    }

    public void setHotelId(double hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getNoNights() {
        return noNights;
    }

    public void setNoNights(int noNights) {
        this.noNights = noNights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;

        Reservation that = (Reservation) o;

        return Double.compare(that.reservationId, reservationId) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(reservationId);
        return (int) (temp ^ (temp >>> 32));
    }
}
