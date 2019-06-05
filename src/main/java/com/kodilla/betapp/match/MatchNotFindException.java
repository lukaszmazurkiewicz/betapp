package com.kodilla.betapp.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MatchNotFindException extends RuntimeException {
    public MatchNotFindException(String message) {
        super(message);
    }
}
