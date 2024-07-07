package app.template.orm.exceptions;

public class PrimaryKeyException extends OrmException {
    public PrimaryKeyException(){super();}
    public PrimaryKeyException(String msg){super(msg);}
}
