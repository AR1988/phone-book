package de.reutow.phonebook.dto.email;

import de.reutow.phonebook.validation.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class EmailToEditDto {

    @Email(message = "{validation.emailPattern.default}")
    @NotBlank(message = "{validation.email.default}")
    public String email;
    public boolean isFavorite;
}
