package app.template.exceptions;

public class ControllerException extends MyException {
    public ControllerException(String message) {
        super("CONTROLLER: "+message);
    }
}
