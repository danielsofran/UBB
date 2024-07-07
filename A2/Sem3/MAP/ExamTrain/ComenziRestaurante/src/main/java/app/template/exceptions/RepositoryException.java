package app.template.exceptions;

public class RepositoryException extends MyException {
    /**
     * Constructor
     * @param message - mesajul de eroare
     */
    public RepositoryException(String message) {
        super("REPO: "+message);
    }
}
