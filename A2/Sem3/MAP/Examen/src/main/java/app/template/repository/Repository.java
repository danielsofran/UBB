package app.template.repository;

import app.template.exceptions.DuplicatedElementException;
import app.template.exceptions.NotExistentException;
import app.template.exceptions.RepositoryException;
import app.template.exceptions.ValidationException;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.DataNotFoundException;
import app.template.orm.exceptions.DuplicateDataException;

import java.util.List;

public class Repository<ID, E>{
    private final Class<E> table;
    private final ORM orm;

    public Repository(Class<E> table, ConnectionManager connectionManager)
    {
        this.table = table;
        orm = new ORM(connectionManager);
    }

    public E findOne(ID id) throws ValidationException, RepositoryException
    {
        if(id == null)
            throw new ValidationException("Id must not be null");
        try{
            E rez = orm.select(table, id);
            return rez;
        }
        catch (DataNotFoundException ex){
            throw new NotExistentException("No id "+id+" found in "+table.getSimpleName());
        }
        catch (ValidationException ex){
            throw new ValidationException(table.getSimpleName()+"("+id+"): "+ex.getMessage());
        }
        catch (Exception ex){ throw new RepositoryException(ex.getMessage()); }
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

    public E save(E entity) throws RepositoryException, ValidationException
    {
        try{
            return orm.insert(entity);
        }
        catch (DuplicateDataException ex) {
            throw new DuplicatedElementException(ex.getMessage());
        }
        catch (ValidationException ve){
            throw ve;
        }
        catch (Exception ex)
        {
            throw new RepositoryException(ex.getMessage());
        }
    }

}
