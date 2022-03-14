package de.reutow.phonebook.repo;

import de.reutow.phonebook.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAddressRepo extends CrudRepository<Address, Long> {

    List<Address> findAllByContactId(long contactId);
}
