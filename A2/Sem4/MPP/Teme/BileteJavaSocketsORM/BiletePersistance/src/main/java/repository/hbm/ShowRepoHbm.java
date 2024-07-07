package repository.hbm;

import bilete.domain.Show;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.ShowRepository;

import java.time.LocalDate;
import java.util.Collection;

public class ShowRepoHbm implements ShowRepository{
    private static SessionFactory sessionFactory;

    public ShowRepoHbm() {
        sessionFactory = SessionSingleton.getInstance();
    }

    @Override
    public int size() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            int size = session.createQuery("select count(*) from bilete.domain.Show", Show.class).list().size();
            session.getTransaction().commit();
            return size;
        }
    }

    @Override
    public void save(Show entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer integer) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from bilete.domain.Show where id=:id")
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Integer integer, Show entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update bilete.domain.Artist set name=:name where id=:id")
                    .setParameter("name", entity.getArtist().getName())
                    .setParameter("id", entity.getArtist().getId())
                    .executeUpdate();
            session.createQuery("update bilete.domain.Show set date=:date, availableSeats=:availableSeats where id=:id")
                    .setParameter("date", entity.getDate())
                    .setParameter("availableSeats", entity.getAvailableSeats())
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Show findOne(Integer integer) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Show show = session.createQuery("from bilete.domain.Show where id=:id", Show.class)
                    .setParameter("id", integer)
                    .list().get(0);
            session.getTransaction().commit();
            return show;
        }
    }

    @Override
    public Collection<Show> findAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Collection<Show> shows = session.createQuery("from bilete.domain.Show", Show.class).list();
            session.getTransaction().commit();
            return shows;
        }
    }

    @Override
    public Collection<Show> findByDay(LocalDate date)
    {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Collection<Show> shows = session.createQuery("from bilete.domain.Show where date=:date", Show.class)
                    .setParameter("date", date)
                    .list();
            session.getTransaction().commit();
            return shows;
        }
    }

}
