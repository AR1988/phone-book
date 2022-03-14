package de.reutow.phonebook.service;

import de.reutow.phonebook.entity.Contact;
import de.reutow.phonebook.entity.Group;
import de.reutow.phonebook.exceptions.ContactNotFoundException;
import de.reutow.phonebook.repo.IContactRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private static final String CONTACT_IS_NOT_FOUND = "contact is not found";
    private final IContactRepo contactRepo;

    public ContactService(IContactRepo repo) {
        this.contactRepo = repo;
    }

    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    public Contact add(String firstName, String lastName, Integer age, Boolean isFavorite, Group group) {
        Contact contact = new Contact(firstName, lastName, age, isFavorite, group);
        return contactRepo.save(contact);
    }

    public Contact getById(long contactId) {

        return contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_IS_NOT_FOUND));
    }

    public void deleteById(long contactId) {
        if (contactRepo.existsById(contactId))
            contactRepo.deleteById(contactId);
        else
            throw new ContactNotFoundException(CONTACT_IS_NOT_FOUND);
    }

    public void editById(String firstName, String lastName, Integer age, Boolean isFavorite, Group group, Long contactId) {
        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_IS_NOT_FOUND));

        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAge(age);
        contact.setFavourite(isFavorite);
        contact.setGroup(group);

        contactRepo.save(contact);

    }

    public List<Contact> searchByFirst(String term) {
        return contactRepo.findAllByFirstNameContainsIgnoreCase(term);
    }
}
