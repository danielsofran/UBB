package service;

public class ParseError extends Exception{

    public ParseError() {
        super();
    }

    public ParseError(String message) {
        super(message);
    }

    public ParseError(String message, Throwable cause) {
        super(message, cause);
    }
}
