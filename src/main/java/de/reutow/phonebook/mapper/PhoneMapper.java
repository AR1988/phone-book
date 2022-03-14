package de.reutow.phonebook.mapper;

import de.reutow.phonebook.dto.phonedto.PhoneToDisplayDto;
import de.reutow.phonebook.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    public PhoneToDisplayDto toDisplayDto(Phone phone) {

        return new PhoneToDisplayDto(
                phone.getId(),
                phone.getCountryCode(),
                phone.getTelephoneNumber(),
                phone.isFavorite(),
                phone.getContact().getId());
    }
}
