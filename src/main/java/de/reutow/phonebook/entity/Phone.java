package de.reutow.phonebook.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.countyCode.default}")
    @Size(max = 10, message = "{validation.telephoneCodeLength.default}")
    private String countryCode;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.telephoneNumber.default}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.telephoneNumberPattern.default}")
    @Size(min = 5, max = 30, message = "{validation.telephoneNumberLength.default}")
    private String telephoneNumber;

    @NonNull
    private Boolean isFavorite;

    @NonNull
    @Setter
    @ManyToOne
    Contact contact;

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Boolean isFavorite() {
        return this.isFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Phone phone = (Phone) o;
        return id != null && Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

