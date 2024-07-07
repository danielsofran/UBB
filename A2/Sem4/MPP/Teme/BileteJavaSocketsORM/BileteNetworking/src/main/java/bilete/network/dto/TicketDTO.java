package bilete.network.dto;

import java.io.Serializable;

public class TicketDTO implements Serializable {
    private Integer showid;
    private Integer seats;

    public TicketDTO(Integer showid, Integer seats) {
        this.showid = showid;
        this.seats = seats;
    }

    public Integer getShowid() {
        return showid;
    }

    public Integer getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "TicketDTO{" + "showid=" + showid + ", seats=" + seats + '}';
    }
}
