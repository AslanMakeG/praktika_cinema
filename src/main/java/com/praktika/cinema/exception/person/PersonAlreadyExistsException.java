package com.praktika.cinema.exception.person;

public class PersonAlreadyExistsException extends Exception{
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
