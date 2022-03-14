package de.reutow.phonebook.mapper;

import de.reutow.phonebook.dto.email.EmailToDisplayDto;
import de.reutow.phonebook.entity.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailToDisplayDto toDisplayDto(Email email) {

        return new EmailToDisplayDto(email.getId(),
                email.getEmail(),
                email.isFavorite(),
                email.getContact().getId());
    }
}
