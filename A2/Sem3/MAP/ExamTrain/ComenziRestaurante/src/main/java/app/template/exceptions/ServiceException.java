package app.template.exceptions;

public class ServiceException extends MyException{
    public ServiceException(String message){
        super("SERVICE: "+message);
    }
}
