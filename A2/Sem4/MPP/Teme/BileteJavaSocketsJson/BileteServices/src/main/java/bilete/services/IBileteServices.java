package bilete.services;

import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.domain.User;

import java.time.LocalDate;

public interface IBileteServices {
    void login(User user, IBileteObserver client) throws BileteException;
    void logout(User user, IBileteObserver client) throws BileteException;
    Show[] findAllArtisti() throws BileteException;
    Show[] findShowsOnDate(LocalDate date) throws BileteException;
    void sellTicket(Ticket ticket) throws BileteException;
}
