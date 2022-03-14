package de.reutow.phonebook.dto.phonedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
public class PhoneToEditDto {

    @NotBlank(message = "{validation.countyCode.default}")
    @Size(max = 10, message = "{validation.telephoneCodeLength.default}")
    @Pattern(regexp = "^\\+\\d+$", message = "{validation.telephoneCodePattern.default}")
    public String countryCode;
    @NotBlank(message = "{validation.telephoneNumber.default}")
    @Pattern(regexp = "^\\d+$", message = "{validation.telephoneNumberPattern.default}")
    @Size(min = 5, max = 30, message = "{validation.telephoneNumberLength.default}")
    public String telephoneNumber;
    public boolean isFavorite;
}
