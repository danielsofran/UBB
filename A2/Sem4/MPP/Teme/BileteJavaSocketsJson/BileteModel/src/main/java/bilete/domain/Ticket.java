package bilete.domain;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements HasID<Integer>, Serializable {
    Integer id;
    Show show;
    String costumerName;
    Integer seats;

    public Ticket() {
    }

    public Ticket(Integer id, Show show, String costumerName, Integer seats) {
        this.id = id;
        this.show = show;
        this.costumerName = costumerName;
        this.seats = seats;
    }

    public Ticket(Show show, String costumerName, Integer seats){
        this.id = -1;
        this.show = show;
        this.costumerName = costumerName;
        this.seats = seats;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Bilet{" + "id=" + id + ", showId=" + show + ", clientName=" + costumerName + ", seats=" + seats + '}';
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
        final Ticket other = (Ticket) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
