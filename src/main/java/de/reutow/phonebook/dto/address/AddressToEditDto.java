package de.reutow.phonebook.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class AddressToEditDto {
    @Schema(example = "USA")
    @NotBlank(message = "{validation.country.default}")
    public String country;

    @Schema(example = "Miami")
    @NotBlank(message = "{validation.city.default}")
    public String city;

    @Schema(example = "3251 S Miami Ave")
    @NotBlank(message = "{validation.address.default}")
    public String address;

    @Schema(example = "33129")
    @NotBlank(message = "{validation.index.default}")
    public String index;

    @Schema(example = "false")
    public boolean isFavorite;
}
