package com.techelevator.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LeagueNameAlreadyExistsException extends RuntimeException {
    public LeagueNameAlreadyExistsException() {
        super("League Name Already Exists");
    }
}
