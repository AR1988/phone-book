package de.reutow.phonebook.mapper;

import de.reutow.phonebook.dto.contactdto.ContactToDisplayDto;
import de.reutow.phonebook.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactToDisplayDto toDto(Contact contact){

        return new ContactToDisplayDto(contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getAge(),
                contact.getIsFavourite(),
                contact.getGroup().getGroupName());
    }
}
