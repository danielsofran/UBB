package app.template.repository;

import app.template.exceptions.DuplicatedElementException;
import app.template.exceptions.NotExistentException;
import app.template.exceptions.RepositoryException;
import app.template.exceptions.ValidationException;
import app.template.models.validation.Validator;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.DataNotFoundException;
import app.template.orm.exceptions.DuplicateDataException;

import java.util.List;
import java.util.function.Predicate;

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

    public Repository(Class<E> table, ConnectionManager connectionManager, Validator<E> validator)
    {
        this.table = table;
        orm = new ORM(connectionManager);
        this.validator = validator;
    }

    public E findOne(ID id) throws ValidationException, RepositoryException
    {
        if(id == null)
            throw new ValidationException("Id must not be null");
        try{
            E rez = orm.select(table, id);
            if(validator!=null)
                validator.validate(rez);
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

    public E findOne(Predicate<E> predicate) throws ValidationException, RepositoryException
    {
        if(predicate == null)
            throw new ValidationException("The predicate must not be null");
        try{
            List<E> rez = orm.select(table);
            E ret = rez.stream().filter(predicate).findFirst().orElse(null);
            if(ret == null)
                throw new DataNotFoundException();
            if(validator!=null)
                validator.validate(ret);
            return ret;
        }
        catch (DataNotFoundException ex){
            throw new NotExistentException("No data found in "+table.getSimpleName());
        }
        catch (ValidationException ex){
            throw new ValidationException(table.getSimpleName()+": "+ex.getMessage());
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
            if(validator != null)
                validator.validate(entity);
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

    public void delete(ID id) throws RepositoryException
    {
        if(id == null)
            throw new ValidationException("Id must not be null");
        try{
            orm.delete(table, id);
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
