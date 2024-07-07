package app.template.repository;

import app.template.exceptions.RepositoryException;
import app.template.exceptions.ValidationException;
import app.template.models.validation.Validator;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;

import java.util.List;

public class Repository<ID, E>{
    private final Class<E> table;
    private final ORM orm;
    private final Validator<E> validator;

    public Repository(Class<E> table, ConnectionManager connectionManager)
    {
        this.table = table;
        orm = new ORM(connectionManager);
        validator = null;
    }

    public List<E> findAll() throws RepositoryException {
        try {
            return orm.select(table);
        }
        catch (Exception ex)
        {
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void update(ID id, E entity) throws ValidationException, RepositoryException
    {
        if(id == null)
            throw new ValidationException("Id must not be null");
        if(entity == null)
            throw new ValidationException("The entity must not be null");
        if(validator!=null)
            validator.validate(entity);
        try{
            orm.update(entity, id);
        }
        catch (Exception ex)
        {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
