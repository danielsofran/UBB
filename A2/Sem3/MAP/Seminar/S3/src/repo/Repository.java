package repo;

public interface Repository<ID, E> {
    E save(E entity);
    E delete(ID id);
    E findOne(ID id);
    Iterable<E> findAll();
}
