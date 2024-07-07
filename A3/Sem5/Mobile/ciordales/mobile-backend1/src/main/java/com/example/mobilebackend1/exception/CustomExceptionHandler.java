package com.example.mobilebackend1.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXISTS = "User with this email already exists";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";
    public static final String TOKEN_EXPIRED = "Token expired";
    public static final String NOT_AUTHORIZED = "Not authorized";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomNotFoundException(UserNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setMessage(INVALID_EMAIL_OR_PASSWORD);
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomNotFoundException(EmailAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setMessage(USER_WITH_THIS_EMAIL_ALREADY_EXISTS);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomNotFoundException(ExpiredJwtException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setMessage(TOKEN_EXPIRED);
        errorResponse.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotAuthorizedException.class, MissingRequestHeaderException.class})
    public ResponseEntity<CustomErrorResponse> handleCustomNotFoundException(NotAuthorizedException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setMessage(NOT_AUTHORIZED);
        errorResponse.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @Data
    public static class CustomErrorResponse {
        private String message;
        private int errorCode;
    }
}