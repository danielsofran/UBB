package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;
import app.template.utils.Comparer;

@DBEntity
public class Location {
    @PK
    double locationId;
    String locationName;

    public Location(){}

    public Location(double locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public double getLocationId() {
        return locationId;
    }

    public void setLocationId(double locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        return Comparer.equal(locationId, location.locationId);
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(locationId);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return locationName;
    }
}
