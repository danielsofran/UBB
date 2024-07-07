package bilete.services;

public class BileteException extends Exception{
    public BileteException() {
    }

    public BileteException(String message) {
        super(message);
    }

    public BileteException(String message, Throwable cause) {
        super(message, cause);
    }
}
