package de.reutow.phonebook.repo;

import de.reutow.phonebook.entity.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEmailRepo extends CrudRepository<Email, Long> {

    List<Email> findAllByContactId(long contactId);
}
