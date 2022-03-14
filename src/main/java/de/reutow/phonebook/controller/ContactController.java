package de.reutow.phonebook.controller;

import de.reutow.phonebook.dto.contactdto.ContactToAddDto;
import de.reutow.phonebook.dto.contactdto.ContactToDisplayDto;
import de.reutow.phonebook.dto.contactdto.ContactToEditDto;
import de.reutow.phonebook.entity.Contact;
import de.reutow.phonebook.entity.Group;
import de.reutow.phonebook.mapper.ContactMapper;
import de.reutow.phonebook.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Contact API")
@RestController
@RequestMapping("api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @Operation(description = "Add a new contact to contact", tags = {"add"})
    @PostMapping
    public ContactToDisplayDto add(@RequestBody @Valid ContactToAddDto contactToAdd) {
        Group group = Group.valueOf(contactToAdd.group.toUpperCase());
        Contact contact = contactService.add(contactToAdd.firstName, contactToAdd.lastName, contactToAdd.age,
                contactToAdd.isFavorite, group);
        return contactMapper.toDto(contact);
    }

    @Operation(description = "Update contact by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid ContactToEditDto contactToEditDto, @PathVariable(name = "id") long contactId) {

        contactService.editById(contactToEditDto.firstName, contactToEditDto.lastName, contactToEditDto.age,
                contactToEditDto.isFavorite,
                Group.valueOf(contactToEditDto.group.toUpperCase()), contactId);
    }

    @Operation(description = "Get contact by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public ContactToDisplayDto getById(@PathVariable(name = "id") long contactId) {

        Contact contact = contactService.getById(contactId);
        return contactMapper.toDto(contact);
    }

    @Operation(description = "Get all contacts", tags = {"get all"})
    @GetMapping
    public List<ContactToDisplayDto> getAllContacts() {

        return contactService.getAllContacts().stream()
                .map(contactMapper::toDto).collect(Collectors.toList());
    }

    @Operation(description = "Remove contact by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long contactId) {

        contactService.deleteById(contactId);
    }

    @Operation(description = "Get contacts by search", tags = {"search"})
    @GetMapping("/search")
    public List<ContactToDisplayDto> searchContactByFirstName(
            @Parameter(name = "searchContact", description = "the symbols which contains contacts that user searches")
            @RequestParam(name = "searchContact") String name) {

        return contactService.searchByFirst(name).stream()
                .map(contactMapper::toDto).collect(Collectors.toList());
    }
}
