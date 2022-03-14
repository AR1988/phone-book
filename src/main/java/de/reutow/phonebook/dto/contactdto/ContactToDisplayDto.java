package de.reutow.phonebook.dto.contactdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ContactToDisplayDto {

    public long id;
    public String firstName;
    public String lastName;
    public int age;
    public boolean isFavorite;
    public String group;
}
