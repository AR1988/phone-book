package de.reutow.phonebook.mapper;

import de.reutow.phonebook.dto.address.AddressToDisplayDto;
import de.reutow.phonebook.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressToDisplayDto toDto(Address address) {

        return new AddressToDisplayDto(address.getId(),
                address.getCountry(),
                address.getCity(),
                address.getAddress(),
                address.getZip(),
                address.isFavorite(),
                address.getContact().getId());
    }
}
