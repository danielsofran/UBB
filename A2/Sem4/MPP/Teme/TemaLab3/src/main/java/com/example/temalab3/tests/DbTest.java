package com.example.temalab3.tests;

import com.example.temalab3.domain.Artist;
import com.example.temalab3.domain.Show;
import com.example.temalab3.domain.Ticket;
import com.example.temalab3.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class DbTest {
    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepoDB("bd.config");
        Artist artist = new Artist(4, "Andrei");
        Artist artist2 = new Artist(8, "Andrei2");
        artistRepository.save(artist2);
        artistRepository.delete(1);
        assert artistRepository.size()==1;
        assert artistRepository.findOne(3).equals(artist2);
        assert artistRepository.findAll().size()==1;

        ShowRepository showRepository = new ShowRepoDB("bd.config");
        Show show = new Show(3, artist, LocalDate.now(), LocalTime.now(), "Africa", 50, 50);
//        showRepository.save(show);
        assert showRepository.size()==1;
        assert showRepository.findOne(1).equals(show);
        assert showRepository.findAll().size()==1;
//        showRepository.delete(1);
//        assert showRepository.size()==0;
//        assert showRepository.findOne(1)==null;
//        assert showRepository.findAll().size()==0;

        TicketRepository ticketRepository = new TicketRepoDB("bd.config");
        Ticket ticket = new Ticket(3, show, "Daniel", 1);
//        ticketRepository.save(ticket);
        assert ticketRepository.size()==1;
        assert ticketRepository.findOne(1).equals(ticket);
        assert ticketRepository.findAll().size()==1;
//        ticketRepository.delete(1);
//        assert ticketRepository.size()==0;
//        assert ticketRepository.findOne(1)==null;
//        assert ticketRepository.findAll().size()==0;
    }
}
