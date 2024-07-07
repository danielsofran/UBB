package app.template.service;

import app.template.exceptions.ServiceException;
import app.template.models.Client;
import app.template.models.Flight;
import app.template.models.Ticket;
import app.template.orm.ConnectionManager;
import app.template.repository.Repository;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyAbstractObservable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Service extends MyAbstractObservable<ChangedEvent<Ticket>> {
    ConnectionManager connectionManager;
    Repository<String, Client> clientRepository;
    Repository<Long, Flight> flightRepository;
    Repository<Integer, Ticket> ticketRepository;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
        clientRepository = new Repository<>(Client.class, connectionManager);
        flightRepository = new Repository<>(Flight.class, connectionManager);
        ticketRepository = new Repository<>(Ticket.class, connectionManager);
    }

//    public List<Client> getClients(){
//        return clientRepository.findAll();
//    }

    public Client login(String username) throws ServiceException {
        if(username == null)
            throw new ServiceException("Username invalid!");
        Client client = clientRepository.findOne(username);
        return client;
    }

    public List<String> getFrom()
    {
        return flightRepository.findAll().stream().map(Flight::getFrom).distinct().collect(Collectors.toList());
    }

    public List<String> getTo()
    {
        return flightRepository.findAll().stream().map(Flight::getTo).distinct().collect(Collectors.toList());
    }

    public List<Flight> getFlights(LocalDate date, String from, String to)
    {
        if(date == null || from == null || to == null)
            throw new ServiceException("Please provide dates!");
        if(from.length() == 0 || to.length() == 0)
            throw new ServiceException("Please provide a location from from and to!");
        return flightRepository.findAll().stream()
                .filter(flight ->
                        flight.getFrom().equals(from) &&
                        flight.getTo().equals(to) &&
                        flight.getDepartureTime().getYear() == date.getYear() &&
                        flight.getDepartureTime().getDayOfYear() == date.getDayOfYear())
                .collect(Collectors.toList());
    }

    public Ticket cumpara(Client client, Flight flight)
    {
        LocalDateTime purchaseTime = LocalDateTime.now();
        if(client == null || flight == null)
            throw new ServiceException("Please provide both client and flight!");
        if(getNrLocuriDisponibile(flight) <= 0)
            throw new ServiceException("Ne pare rau, nu mai avem locuri disponibile!");
        Ticket ticket = new Ticket(client.getUsername(), flight.getFlightId(), purchaseTime);
        ticket = ticketRepository.save(ticket);
        notifyObservers(new ChangedEvent<>(ChangeEventType.ADDED, ticket));
        return ticket;
    }

    public int getNrLocuriDisponibile(Flight flight)
    {
        if(flight == null)
            throw new ServiceException("Please provide non null flight!");
        long achizitionate = ticketRepository.findAll().stream().filter(ticket -> ticket.getFlightId().equals(flight.getFlightId())).count();
        return (int) (flight.getSeats() - achizitionate);
    }
}
