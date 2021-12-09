package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CourseNameAlreadyExistsException extends RuntimeException {
    public CourseNameAlreadyExistsException() {
        super("Course Name Already Exists");
    }
}
