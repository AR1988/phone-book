package de.reutow.phonebook.entity;


import de.reutow.phonebook.validation.MinMax;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.firstname.default}")
    private String firstName;

    @NonNull
    @Setter
    @NotBlank(message = "{validation.lastname.default}")
    private String lastName;

    @NonNull
    @Setter
    @MinMax(minAge = 1, maxAge = 120, message = "{validation.age.default}")
    private Integer age;

    @NonNull
    @Setter
    private Boolean isFavourite;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_GROUP")
    private Group group;

    public Contact(@NonNull String firstName, @NonNull String lastName, @NonNull Integer age, @NonNull Boolean isFavourite, @NonNull Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isFavourite = isFavourite;
        this.group = group;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Address> addresses = new ArrayList<>();

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setFavourite(Boolean favorite) {
        isFavourite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contact contact = (Contact) o;
        return id != null && Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
