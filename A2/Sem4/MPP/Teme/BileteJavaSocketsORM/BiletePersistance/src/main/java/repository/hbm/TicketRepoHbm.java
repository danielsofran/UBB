package repository.hbm;

import bilete.domain.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.TicketRepository;

import java.util.Collection;

public class TicketRepoHbm implements TicketRepository {
    private static SessionFactory sessionFactory;

    public TicketRepoHbm() {
        sessionFactory = SessionSingleton.getInstance();
    }

    @Override
    public int size() {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            int size = session.createQuery("select count(*) from bilete.domain.Ticket", bilete.domain.Ticket.class).list().size();
            session.getTransaction().commit();
            return size;
        }
    }

    @Override
    public void save(bilete.domain.Ticket entity) {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer integer) {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.createQuery("delete from bilete.domain.Ticket where id=:id")
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Integer integer, bilete.domain.Ticket entity) {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.createQuery("update bilete.domain.Artist set name=:name where id=:id")
                    .setParameter("name", entity.getShow().getArtist().getName())
                    .setParameter("id", entity.getShow().getArtist().getId())
                    .executeUpdate();
            session.createQuery("update bilete.domain.Show set date=:date, location=:location, beginTime=:beginTime, availableSeats=:availableSeats, soldSeats=:soldSeats where id=:id")
                    .setParameter("date", entity.getShow().getDate())
                    .setParameter("location", entity.getShow().getLocation())
                    .setParameter("beginTime", entity.getShow().getBeginTime())
                    .setParameter("availableSeats", entity.getShow().getAvailableSeats())
                    .setParameter("soldSeats", entity.getShow().getSoldSeats())
                    .setParameter("id", entity.getShow().getId())
                    .executeUpdate();
            session.createQuery("update bilete.domain.Ticket set costumerName=:costumerName, seats=:seats where id=:id")
                    .setParameter("costumerName", entity.getCostumerName())
                    .setParameter("seats", entity.getSeats())
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public bilete.domain.Ticket findOne(Integer integer) {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            bilete.domain.Ticket ticket = session.createQuery("from bilete.domain.Ticket where id=:id", bilete.domain.Ticket.class)
                    .setParameter("id", integer)
                    .setMaxResults(1)
                    .uniqueResult();
            session.getTransaction().commit();
            return ticket;
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            Collection<bilete.domain.Ticket> tickets = session.createQuery("from bilete.domain.Ticket", bilete.domain.Ticket.class).list();
            session.getTransaction().commit();
            return tickets;
        }
    }
}
