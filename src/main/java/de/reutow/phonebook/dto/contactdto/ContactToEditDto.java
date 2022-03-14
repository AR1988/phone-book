package de.reutow.phonebook.dto.contactdto;

import de.reutow.phonebook.validation.MinMax;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class ContactToEditDto {

    @NotBlank(message = "{validation.firstname.default}")
    public String firstName;
    @NotBlank(message = "{validation.lastname.default}")
    public String lastName;
    @MinMax(minAge = 1, maxAge = 120, message = "{validation.age.default}")
    public int age;
    public boolean isFavorite;
    @NotBlank(message = "{validation.group.default}")
    public String group;
}
