package repo;

import models.validators.Validator;

import java.util.Map;

public abstract class InMemoryRepository<ID, E extends HasId<ID>> implements Repository<ID, E> {
    private Map<ID, E> entities;
    private Validator<E> validator;

    @Override
    public E save(E entity) {
        if(entity == null) throw new IllegalArgumentException("entity must not be null");
        if(entities.containsKey(entity.getID())) return entity;
        validator.validate(entity);
        entities.put(entity.getID(), entity);
        return entity;
    }

    @Override
    public E delete(ID id) {
        return null;
    }

    @Override
    public E findOne(ID id) {
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return null;
    }
}