package de.reutow.phonebook.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.country.default}")
    private String country;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.city.default}")
    private String city;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.address.default}")
    private String address;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.index.default}")
    private String zip;

    @NonNull
    private Boolean isFavorite;

    @NonNull
    @Setter
    @ManyToOne
    @JoinColumn
    private Contact contact;

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
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
