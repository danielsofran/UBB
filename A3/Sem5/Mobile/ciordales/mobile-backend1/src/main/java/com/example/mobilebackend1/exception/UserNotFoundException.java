package com.example.mobilebackend1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason= "Invalid email or password")
public class UserNotFoundException extends RuntimeException {
}
