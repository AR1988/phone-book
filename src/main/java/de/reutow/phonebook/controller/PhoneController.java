package de.reutow.phonebook.controller;

import de.reutow.phonebook.dto.phonedto.PhoneToAddDto;
import de.reutow.phonebook.dto.phonedto.PhoneToDisplayDto;
import de.reutow.phonebook.dto.phonedto.PhoneToEditDto;
import de.reutow.phonebook.entity.Phone;
import de.reutow.phonebook.mapper.PhoneMapper;
import de.reutow.phonebook.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Phone API")
@RestController
@RequestMapping("api/phones")
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @Operation(description = "Add a new phone to contact", tags = {"add"})
    @PostMapping
    public PhoneToDisplayDto add(@RequestBody @Valid PhoneToAddDto phoneToAddDto) {
        Phone phone = phoneService.addPhone(
                phoneToAddDto.countryCode,
                phoneToAddDto.telephoneNumber,
                phoneToAddDto.isFavorite,
                phoneToAddDto.contactId);
        return phoneMapper.toDisplayDto(phone);
    }

    @Operation(description = "Update phone by id", tags = {"edit"})
    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid PhoneToEditDto phoneToEditDto, @PathVariable(name = "id") long phoneId) {

        phoneService.editById(
                phoneToEditDto.countryCode,
                phoneToEditDto.telephoneNumber,
                phoneToEditDto.isFavorite,
                phoneId);
    }

    @Operation(description = "Get phone by id", tags = {"get by id"})
    @GetMapping("/{id}")
    public PhoneToDisplayDto getById(@PathVariable(name = "id") long phoneId) {
        Phone phone = phoneService.getById(phoneId);
        return phoneMapper.toDisplayDto(phone);
    }

    @Operation(description = "Get all phones by contact id", tags = {"get all"})
    @GetMapping("/{id}/all")
    public List<PhoneToDisplayDto> getAllByContactId(@PathVariable(name = "id") long contactId) {
        List<Phone> phones = phoneService.getAllByContactId(contactId);
        return phones.stream().map(phoneMapper::toDisplayDto).collect(Collectors.toList());
    }

    @Operation(description = "Remove phone by id", tags = {"remove"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long phoneId) {
        phoneService.deleteById(phoneId);
    }
}
