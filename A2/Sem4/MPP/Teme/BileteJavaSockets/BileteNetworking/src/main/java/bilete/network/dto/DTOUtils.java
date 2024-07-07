package bilete.network.dto;

import bilete.domain.Ticket;
import bilete.domain.User;

public class DTOUtils {
    public static User getFromDto(UserDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        return new User(username, password);
    }

    public static UserDTO getDto(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return new UserDTO(username, password);
    }

    public static TicketDTO getDTO(Ticket ticket) {
        Integer showid = ticket.getShow().getId();
        Integer seats = ticket.getSeats();
        return new TicketDTO(showid, seats);
    }
}
