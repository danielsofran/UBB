package app.template.exceptions;

public class NotExistentException extends RepositoryException {
    /**
     * Constructor
     * @param message - mesajul de eroare
     */
    public NotExistentException(String message) {
        super(message);
    }
}
