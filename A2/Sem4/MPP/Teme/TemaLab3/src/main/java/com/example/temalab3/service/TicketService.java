package com.example.temalab3.service;

import com.example.temalab3.domain.Show;
import com.example.temalab3.domain.Ticket;
import com.example.temalab3.exceptions.RepoException;
import com.example.temalab3.exceptions.ServiceException;
import com.example.temalab3.repository.ShowRepository;
import com.example.temalab3.repository.TicketRepository;

public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;

    public TicketService(TicketRepository ticketRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
    }

    public void sellTicket(Show show, Integer seats, String costumerName) throws RepoException
    {
        Ticket ticket = new Ticket(show, costumerName, seats);
        if(show == null || show.getArtist() == null || costumerName == null || costumerName.length() == 0 || seats <= 0)
            throw new ServiceException("Ticket invalid!");
        if(show.getAvailableSeats() < seats)
            throw new ServiceException("Not enough seats!");
        ticketRepository.save(ticket);
        show.setAvailableSeats(show.getAvailableSeats() - seats);
        show.setSoldSeats(show.getSoldSeats() + seats);
        showRepository.update(show.getId(), show);
    }
}
