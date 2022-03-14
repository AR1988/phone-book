package de.reutow.phonebook.repo;

import de.reutow.phonebook.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContactRepo extends CrudRepository<Contact, Long> {

    List<Contact> findAll();
    List<Contact> findAllByFirstNameContainsIgnoreCase(String term);
}
