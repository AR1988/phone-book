package de.reutow.phonebook.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToAddDto {
    @Schema(example = "Germany")
    @NotBlank(message = "{validation.country.default}")
    public String country;

    @Schema(example = "Berlin")
    @NotBlank(message = "{validation.city.default}")
    public String city;

    @Schema(example = "Hauptstrasse 11")
    @NotBlank(message = "{validation.address.default}")
    public String address;

    @Schema(example = "10369")
    @NotBlank(message = "{validation.index.default}")
    public String index;

    @Schema(example = "false")
    public boolean isFavorite;

    @Schema(description = "Unique identifier of the contact", example = "1")
    @Positive(message = "{validation.contactId.default}")
    public long contactId;
}
