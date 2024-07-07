package app.template.models;

import app.template.orm.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DBEntity
public class Ticket {
    @PK @AutoInc
    int id;
    @FK(Table = Client.class, RefCol = "username")
    String username;
    @FK(Table = Flight.class, RefCol = "flightId")
    Long flightId;
    LocalDateTime purchaseTime;

    public Ticket (){}

    public Ticket(String username, Long flightId, LocalDateTime purchaseTime) {
        this.username = username;
        this.flightId = flightId;
        this.purchaseTime = purchaseTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
