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
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Setter
    @de.reutow.phonebook.validation.Email(message = "{validation.emailPattern.default}")
    @NotBlank(message = "{validation.email.default}")
    private String email;

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
        Email email = (Email) o;
        return id != null && Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
