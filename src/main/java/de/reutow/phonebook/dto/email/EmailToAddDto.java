package de.reutow.phonebook.dto.email;

import de.reutow.phonebook.validation.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToAddDto {

    @Email(message = "{validation.emailPattern.default}")
    @NotBlank(message = "{validation.email.default}")
    public String email;
    public boolean isFavorite;
    @Positive(message = "{validation.contactId.default}")
    public long contactId;
}
