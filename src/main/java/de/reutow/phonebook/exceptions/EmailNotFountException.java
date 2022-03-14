package de.reutow.phonebook.exceptions;

public class EmailNotFountException extends RuntimeException {
    public EmailNotFountException(String message) {
        super(message);
    }
}
