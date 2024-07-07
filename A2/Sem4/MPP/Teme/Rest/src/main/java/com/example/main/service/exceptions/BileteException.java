package com.example.main.service.exceptions;

public class BileteException extends Exception{
    public BileteException(String message) {
        super(message);
    }
    public BileteException(String message, Throwable cause) {
        super(message, cause);
    }
    public BileteException() {
        super();
    }
}
