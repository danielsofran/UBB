package app.template.service;

import app.template.exceptions.ServiceException;
import app.template.models.Localitate;
import app.template.models.Rau;
import app.template.models.Riscuri;
import app.template.orm.ConnectionManager;
import app.template.repository.Repository;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyAbstractObservable;

import java.util.List;
import java.util.stream.Collectors;

public class Service extends MyAbstractObservable<ChangedEvent<Rau>> {
    ConnectionManager connectionManager;
    Repository<String, Rau> rauRepository;
    Repository<String, Localitate> localitateRepository;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
        rauRepository = new Repository<>(Rau.class, connectionManager);
        localitateRepository = new Repository<>(Localitate.class, connectionManager);
    }

    public List<Rau> findAllRauri(){
        return rauRepository.findAll();
    }

    public List<Localitate> findLocalitati(Riscuri riscuri)
    {
        return localitateRepository.findAll().stream().filter(localitate -> {
           int cotaRau = localitate.getRau().getCotaMedie();
           if(cotaRau <= localitate.getCotaMinimaDeRisc() && riscuri == Riscuri.Scazut)
               return true;
            if(cotaRau > localitate.getCotaMinimaDeRisc() && cotaRau <= localitate.getCotaMaximaAdmisa() && riscuri == Riscuri.Mediu)
                return true;
            if(cotaRau > localitate.getCotaMaximaAdmisa() && riscuri == Riscuri.Ridicat)
                return true;
            return false;
        }).collect(Collectors.toList());
    }

    public void updateRau(Rau rau, Integer cota)
    {
        if(rau == null || cota == null)
            throw new ServiceException("Please provide rau and cota!");
        rau.setCotaMedie(cota);
        rauRepository.update(rau.getNume(), rau);
        notifyObservers(new ChangedEvent<>(ChangeEventType.ADDED, rau));
    }
}
