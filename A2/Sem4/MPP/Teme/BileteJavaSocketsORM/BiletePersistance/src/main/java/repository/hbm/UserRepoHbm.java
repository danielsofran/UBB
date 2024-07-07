package repository.hbm;

import bilete.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.UserRepository;

import java.util.Collection;

public class UserRepoHbm implements UserRepository {
    private static SessionFactory sessionFactory;
    public UserRepoHbm() {
        sessionFactory = SessionSingleton.getInstance();
    }
    @Override
    public int size() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            int size = session.createQuery("select count(*) from bilete.domain.User", User.class).list().size();
            session.getTransaction().commit();
            return size;
        }
    }

    @Override
    public void save(User entity) {
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
            session.createQuery("delete from bilete.domain.User where id=:id")
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Integer integer, User entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update bilete.domain.User set username=:username, password=:password where id=:id")
                    .setParameter("username", entity.getUsername())
                    .setParameter("password", entity.getPassword())
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public User findOne(Integer integer) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.createQuery("from bilete.domain.User where id=:id", User.class)
                    .setParameter("id", integer)
                    .list().get(0);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public Collection<User> findAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Collection<User> users = session.createQuery("from bilete.domain.User", User.class).list();
            session.getTransaction().commit();
            return users;
        }
    }


    @Override
    public User login(String username, String password) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.createQuery("from bilete.domain.User where username=:username and password=:password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .list().get(0);
            session.getTransaction().commit();
            return user;
        }
    }
}
