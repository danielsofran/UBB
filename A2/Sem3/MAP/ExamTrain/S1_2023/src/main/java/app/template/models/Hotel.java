package app.template.models;

import app.template.orm.annotations.AutoInc;
import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.FK;
import app.template.orm.annotations.PK;

@DBEntity
public class Hotel {
    @PK
    double hotelId;
    @FK(Table = Location.class, RefCol = "locationId")
    double locationId;
    String hotelName;
    int noRooms;
    double pricePerNight;
    HotelType type;

    public Hotel(){}

    public Hotel(double hotelID, double locationId, String hotelName, int noRooms, double pricePerNight, HotelType type) {
        this.hotelId = hotelID;
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public double getHotelId() {
        return hotelId;
    }

    public void setHotelId(double hotelId) {
        this.hotelId = hotelId;
    }

    public double getLocationId() {
        return locationId;
    }

    public void setLocationId(double locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public HotelType getType() {
        return type;
    }

    public void setType(HotelType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        return Double.compare(hotel.hotelId, hotelId) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(hotelId);
        return (int) (temp ^ (temp >>> 32));
    }
}
