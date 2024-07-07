package app.template.exceptions;

public class MyException extends RuntimeException {
    /**
     * Constructor
     * @param message - mesajul de eroare
     */
    public MyException(String message) {
        super(message);
    }
}
