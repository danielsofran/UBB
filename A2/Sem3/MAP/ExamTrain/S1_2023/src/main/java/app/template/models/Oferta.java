package app.template.models;

import java.time.LocalDate;

public class Oferta {
    String numeHotel;
    String numeLocatie;
    LocalDate startDate;
    LocalDate endDate;

    public Oferta(String numeHotel, String numeLocatie, LocalDate startDate, LocalDate endDate) {
        this.numeHotel = numeHotel;
        this.numeLocatie = numeLocatie;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getNumeHotel() {
        return numeHotel;
    }

    public void setNumeHotel(String numeHotel) {
        this.numeHotel = numeHotel;
    }

    public String getNumeLocatie() {
        return numeLocatie;
    }

    public void setNumeLocatie(String numeLocatie) {
        this.numeLocatie = numeLocatie;
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
}
