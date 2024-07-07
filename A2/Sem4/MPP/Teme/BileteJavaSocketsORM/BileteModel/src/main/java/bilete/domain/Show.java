package bilete.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "shows")
public class Show implements HasID<Integer>, Serializable {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    Integer id;
    @ManyToOne
    Artist artist;
    LocalDate date;
    LocalTime beginTime;
    String location;
    Integer availableSeats;
    Integer soldSeats;

    public Show(){
    }

    public Show(Integer id, Artist artist, LocalDate date, LocalTime beginTime, String location, Integer availableSeats, Integer soldSeats) {
        this.id = id;
        this.artist = artist;
        this.date = date;
        this.beginTime = beginTime;
        this.location = location;
        this.availableSeats = availableSeats;
        this.soldSeats = soldSeats;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Temporal(TemporalType.DATE)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIME)
    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getSoldSeats() {
        return soldSeats;
    }

    public void setSoldSeats(Integer soldSeats) {
        this.soldSeats = soldSeats;
    }

    @Override
    public String toString() {
        return "Show{" + "id=" + id + ", artistId=" + artist + ", date=" + date + ", beginTime=" + beginTime + ", location=" + location + ", availableSeats=" + availableSeats + ", soldSeats=" + soldSeats + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Show other = (Show) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
