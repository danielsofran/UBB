package bilete.server;

import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.domain.User;
import bilete.services.BileteException;
import bilete.services.IBileteObserver;
import bilete.services.IBileteServices;
import repository.ShowRepository;
import repository.TicketRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BileteServerImpl implements IBileteServices {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private TicketRepository ticketRepository;
    private Map<String, IBileteObserver> loggedClients;

    public BileteServerImpl(UserRepository userRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
        this.loggedClients = new ConcurrentHashMap<>();
    }

    public synchronized void login(User user, IBileteObserver client) throws BileteException {
        User userR=userRepository.login(user.getUsername(), user.getPassword());
        if (userR!=null){
            if(loggedClients.get(user.getUsername())!=null)
                throw new BileteException("User already logged in.");
            loggedClients.put(user.getUsername(), client);
        }else
            throw new BileteException("Authentication failed.");
    }

    public synchronized void logout(User user, IBileteObserver client) throws BileteException {
        IBileteObserver localClient=loggedClients.remove(user.getUsername());
        if (localClient==null)
            throw new BileteException("User "+user.getId()+" is not logged in.");
    }

    public Show[] findAllArtisti() {
        Collection<Show> shows = showRepository.findAll();
        return shows.toArray(new Show[shows.size()]);
    }

    public Show[] findShowsOnDate(LocalDate date) {
        Collection<Show> shows = showRepository.findByDay(date);
        return shows.toArray(new Show[shows.size()]);
    }


    public synchronized void sellTicket(Ticket ticket) throws BileteException {
        if(ticket.getShow() == null || ticket.getShow().getArtist() == null ||
        ticket.getCostumerName() == null || ticket.getCostumerName().length() == 0 || ticket.getSeats() <= 0)
            throw new BileteException("Ticket invalid!");
        if(ticket.getShow().getAvailableSeats() < ticket.getSeats())
            throw new BileteException("Not enough seats!");
        ticketRepository.save(ticket);
        Show show = ticket.getShow();
        show.setAvailableSeats(show.getAvailableSeats() - ticket.getSeats());
        show.setSoldSeats(show.getSoldSeats() + ticket.getSeats());
        showRepository.update(show.getId(), show);

        System.out.println("Notifying clients...");
        //notifyClientsExecutor(ticket);
        for(IBileteObserver client : loggedClients.values()) {
            if (client != null) {
                try {
                    System.out.println("Notifying client " + client);
                    client.bileteUpdated(ticket);
                }
                catch (BileteException e) {
                    System.err.println("Error notifying client " + e);
                }// sending response
            }
        }
    }

    private final int defaultThreadsNo=5;
    private synchronized void notifyClientsExecutor(Ticket ticket) {
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for(IBileteObserver client : loggedClients.values()) {
            if (client != null)
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying client " + client);
                        System.out.println("Client class" + client.getClass().getName());
                        client.bileteUpdated(ticket);
                    } catch (BileteException e) {
                        System.err.println("Error notifying client " + e);
                    }
                });
        }
    }
}
