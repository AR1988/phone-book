package de.reutow.phonebook.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
