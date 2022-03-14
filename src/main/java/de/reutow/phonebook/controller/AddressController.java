package de.reutow.phonebook.controller;

import de.reutow.phonebook.dto.address.AddressToAddDto;
import de.reutow.phonebook.dto.address.AddressToDisplayDto;
import de.reutow.phonebook.dto.address.AddressToEditDto;
import de.reutow.phonebook.entity.Address;
import de.reutow.phonebook.mapper.AddressMapper;
import de.reutow.phonebook.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @Operation(description = "Add a new address to contact", tags = {"add"})
    @PostMapping
    public AddressToDisplayDto add(@RequestBody @Valid
                                   @Parameter(description = "Address information for a new address to be created") AddressToAddDto addressToAdd) {
        Address address = addressService.createAddress(addressToAdd.country,
                addressToAdd.city,
                addressToAdd.address,
                addressToAdd.index,
                addressToAdd.isFavorite,
                addressToAdd.contactId);
        return addressMapper.toDto(address);
    }

    @Operation(description = "Update address by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid @Parameter(name = "AddressToEditDto", description = "Address information to be updated") AddressToEditDto addressToAdd,
                     @Parameter(name = "id", example = "1", description = "Address id to be update") @PathVariable(name = "id") long addressId) {
        addressService.editAddressById(addressToAdd.country, addressToAdd.city, addressToAdd.address, addressToAdd.index,
                addressToAdd.isFavorite, addressId);
    }

    @Operation(description = "Get address by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public AddressToDisplayDto getById(@Parameter(description = "address id") @PathVariable(name = "id") long addressId) {
        Address address = addressService.getAddressById(addressId);
        return addressMapper.toDto(address);
    }

    @Operation(description = "Get all addresses by contact id", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<AddressToDisplayDto> getAllByContact(@Parameter(name = "id", description = "Contact id to find all address by this contact id", example = "1") @PathVariable(name = "id") long contactId) {
        List<Address> address = addressService.getAllAddressOfContact(contactId);
        return address.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @Operation(description = "Remove address by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long addressId) {
        addressService.deleteAddressById(addressId);
    }
}
