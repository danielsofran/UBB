package app.template.service;

import app.template.exceptions.RepositoryException;
import app.template.exceptions.ServiceException;
import app.template.models.*;
import app.template.orm.ConnectionManager;
import app.template.repository.Repository;
import app.template.utils.Comparer;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyAbstractObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Service extends MyAbstractObservable<ChangedEvent<Reservation>> {
    ConnectionManager connectionManager;
    Repository<Double, Location> repoLocation;
    Repository<Double, Hotel> repoHotel;
    Repository<Double, SpecialOffer> repoSpecialOffer;
    Repository<Long, Client> repoClients;
    Repository<Double, Reservation> repoReservation;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
        repoLocation = new Repository<>(Location.class, connectionManager);
        repoHotel = new Repository<>(Hotel.class, connectionManager);
        repoSpecialOffer = new Repository<>(SpecialOffer.class, connectionManager);
        repoClients = new Repository<>(Client.class, connectionManager);
        repoReservation = new Repository<>(Reservation.class, connectionManager);
    }

    public List<Location> getLocations(){
        return repoLocation.findAll();
    }

    public List<Hotel> getHotels(){
        return repoHotel.findAll();
    }

    public List<Hotel> getHotels(Location location){
        return repoHotel.findAll().stream().filter(h -> Comparer.equal(location.getLocationId(), h.getLocationId())).collect(Collectors.toList());
    }

    public List<SpecialOffer> getSpecialOffers(LocalDate start, LocalDate end, Hotel hotel)
    {
        return repoSpecialOffer.findAll().stream().filter(so -> {
            if(so.getHotelId() != hotel.getHotelId())
                return false;
            return so.getStartDate().isAfter(start) && so.getStartDate().isBefore(end) ||
                    so.getEndDate().isBefore(end) && so.getEndDate().isAfter(start);
        }).collect(Collectors.toList());
    }

    public Client getClient(Long id)
    {
        return repoClients.findOne(id);
    }

    public List<Oferta> getOferte(Client client) throws RepositoryException
    {
        List<Oferta> rez = new LinkedList<>();
        for(SpecialOffer specialOffer : repoSpecialOffer.findAll())
        {
            if(LocalDate.now().isAfter(specialOffer.getStartDate()) &&
                LocalDate.now().isBefore(specialOffer.getEndDate()) &&
                client.getFidelityGrade() > specialOffer.getPercents())
            {
                Hotel hotel = repoHotel.findOne(specialOffer.getHotelId());
                Location location = repoLocation.findOne(hotel.getLocationId());
                rez.add(new Oferta(hotel.getHotelName(), location.getLocationName(), specialOffer.getStartDate(), specialOffer.getEndDate()));
            }
        }
        return rez;
    }

    public void rezerva(Client client, Hotel hotel, SpecialOffer offer, LocalDateTime startDate, Integer nrNopti)
    {
        if(client == null || hotel == null || offer == null || startDate == null || nrNopti == null)
            throw new ServiceException("Can not reserve!");
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(offer.getStartDate().atStartOfDay()) || now.isAfter(offer.getEndDate().atStartOfDay()))
            throw new ServiceException("This offer is no longer valid!");
        if(client.getFidelityGrade() <= offer.getPercents())
            throw new ServiceException("Prea infidel!");
        double id = repoReservation.findAll().stream().map(Reservation::getReservationId).max(Double::compareTo).orElse(0.0);
        id += 1;
        Reservation reservation = new Reservation(id, client.getClientId(), hotel.getHotelId(), startDate, nrNopti);
        reservation = repoReservation.save(reservation);
        notifyObservers(new ChangedEvent<Reservation>(ChangeEventType.ADDED, reservation));
    }
}
