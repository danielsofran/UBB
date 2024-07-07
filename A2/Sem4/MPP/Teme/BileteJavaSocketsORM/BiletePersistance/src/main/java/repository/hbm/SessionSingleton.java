package repository.hbm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionSingleton {
    private static SessionFactory instance = null;

    static SessionFactory createSession() {
        System.out.println("Hello from SessionFactory");
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try{
            instance = new org.hibernate.boot.MetadataSources( registry ).buildMetadata().buildSessionFactory();
            return instance;
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
            return null;
        }
    }

    public static SessionFactory getInstance() {
        if(instance == null) {
            instance = createSession();
        }
        return instance;
    }

    public static void close() {
        if(instance != null) {
            instance.close();
        }
    }

}
