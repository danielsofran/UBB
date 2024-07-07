package bilete.services;

import bilete.domain.Ticket;

public interface IBileteObserver {
    void bileteUpdated(Ticket ticket) throws BileteException;
}
