package app.template.exceptions;

public class DuplicatedElementException extends RepositoryException {
    /**
     * Constructor
     * @param message - mesajul de eroare
     */
    public DuplicatedElementException(String message) {
        super(message);
    }
}
