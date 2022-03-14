package de.reutow.phonebook.controller;

import de.reutow.phonebook.dto.email.EmailToAddDto;
import de.reutow.phonebook.dto.email.EmailToDisplayDto;
import de.reutow.phonebook.dto.email.EmailToEditDto;
import de.reutow.phonebook.entity.Email;
import de.reutow.phonebook.mapper.EmailMapper;
import de.reutow.phonebook.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Email API")
@RestController
@RequestMapping("api/emails")
public class EmailController {
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
    }

    @Operation(description = "Add a new email to contact", tags = {"add"})
    @PostMapping
    public EmailToDisplayDto add(@RequestBody @Valid EmailToAddDto emailToAddDto) {
        Email email = emailService.addEmail(emailToAddDto.email, emailToAddDto.isFavorite, emailToAddDto.contactId);
        return emailMapper.toDisplayDto(email);
    }

    @Operation(description = "Update email by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid EmailToEditDto toEditDto, @PathVariable(name = "id") long emailId) {
        emailService.editEmailById(toEditDto.email, toEditDto.isFavorite, emailId);
    }

    @Operation(description = "Get email by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public EmailToDisplayDto getById(@PathVariable(name = "id") long emailId) {
        Email email = emailService.getEmailById(emailId);
        return emailMapper.toDisplayDto(email);
    }

    @Operation(description = "Get all emails by contact id", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<EmailToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Email> emails = emailService.getAllEmailsByContactId(contactId);
        return emails.stream().map(emailMapper::toDisplayDto).collect(Collectors.toList());
    }

    @Operation(description = "Remove email by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long emailId) {
        emailService.deleteEmailById(emailId);
    }
}
