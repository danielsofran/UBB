package app.template.exceptions;

public class ValidationException extends MyException {
    /**
     * Constructor
     * @param message - mesajul de eroare
     */
    public ValidationException(String message) {
        super("VALIDATION: "+message);
    }
}
