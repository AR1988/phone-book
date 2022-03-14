package de.reutow.phonebook.repo;

import de.reutow.phonebook.entity.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPhoneRepo extends CrudRepository<Phone, Long> {

    List<Phone> findAllByContactId(long contactId);
}
