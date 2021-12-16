package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User Does Not Exist");
    }
}
