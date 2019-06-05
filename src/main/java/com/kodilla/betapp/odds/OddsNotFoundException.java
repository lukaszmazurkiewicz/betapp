package com.kodilla.betapp.odds;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OddsNotFoundException extends RuntimeException {
    public OddsNotFoundException(String message) {
        super(message);
    }
}
