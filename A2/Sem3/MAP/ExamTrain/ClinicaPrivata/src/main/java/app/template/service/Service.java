package app.template.service;

import app.template.exceptions.ServiceException;
import app.template.models.Consultatie;
import app.template.models.Medic;
import app.template.models.Sectie;
import app.template.models.validation.ConsultatieValidator;
import app.template.orm.ConnectionManager;
import app.template.repository.Repository;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyAbstractObservable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Service extends MyAbstractObservable<ChangedEvent<Consultatie>> {
    ConnectionManager connectionManager;
    Repository<Integer, Sectie> sectieRepository;
    Repository<Integer, Medic> medicRepository;
    Repository<Integer, Consultatie> consultatieRepository;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;

        sectieRepository = new Repository<>(Sectie.class, connectionManager);
        medicRepository = new Repository<>(Medic.class, connectionManager);
        consultatieRepository = new Repository<>(Consultatie.class, connectionManager, new ConsultatieValidator());
    }

    public List<Sectie> getAllSectii(){
        return sectieRepository.findAll();
    }

    public List<Medic> getAllMedici(){
        return medicRepository.findAll();
    }

    public List<Consultatie> getConsultatii(Medic medic)
    {
        return consultatieRepository.findAll().stream()
                .filter(consultatie -> consultatie.getIdMedic() == medic.getId())
                .filter(consultatie -> LocalDateTime.of(consultatie.getData(), consultatie.getOra()).isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public List<Medic> getMedici(Sectie sectie)
    {
        return medicRepository.findAll().stream()
                .filter(medic -> medic.getIdSectie() == sectie.getId())
                .collect(Collectors.toList());
    }

    public void programeaza(Medic medic, String cnp, String nume, LocalDate data, LocalTime ora)
    {
        if(medic == null)
            throw new ServiceException("Please select a medic!");
        Consultatie consultatie = new Consultatie(medic.getId(), cnp, nume, data, ora);
        consultatie = consultatieRepository.save(consultatie);
        notifyObservers(new ChangedEvent<>(ChangeEventType.ADDED, consultatie));
    }
}
