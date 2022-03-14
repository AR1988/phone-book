package de.reutow.phonebook.service;

import de.reutow.phonebook.entity.Contact;
import de.reutow.phonebook.entity.Email;
import de.reutow.phonebook.exceptions.ContactNotFoundException;
import de.reutow.phonebook.exceptions.EmailNotFountException;
import de.reutow.phonebook.repo.IContactRepo;
import de.reutow.phonebook.repo.IEmailRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private static final String EMAIL_NOT_FOUND = "email is not found";
    private static final String CONTACT_NOT_FOUND = "required contact is not found";
    private IEmailRepo emailRepo;
    private IContactRepo contactRepo;

    public EmailService(IEmailRepo emailRepo, IContactRepo contactRepo) {
        this.emailRepo = emailRepo;
        this.contactRepo = contactRepo;
    }

    public List<Email> getAllEmailsByContactId(long contactId) {

        if(!contactRepo.existsById(contactId))
            throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        return emailRepo.findAllByContactId(contactId);
    }

    public void editEmailById(String email, boolean isFavorite, long emailId) {

        Email email1ToEdit = emailRepo.findById(emailId)
                .orElseThrow(() -> new EmailNotFountException(EMAIL_NOT_FOUND));

        email1ToEdit.setEmail(email);
        email1ToEdit.setFavorite(isFavorite);

        emailRepo.save(email1ToEdit);
    }

    public Email addEmail(String email, boolean isFavorite, long contactId) {

        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_NOT_FOUND));
        Email newEmail = new Email(email.toLowerCase().trim(), isFavorite, contact);
        return emailRepo.save(newEmail);
    }

    public Email getEmailById(long emailId) {

        return emailRepo.findById(emailId)
                .orElseThrow(() -> new EmailNotFountException(EMAIL_NOT_FOUND));
    }

    public void deleteEmailById(long emailId) {

        if (emailRepo.existsById(emailId))
            emailRepo.deleteById(emailId);
        else
            throw new EmailNotFountException(EMAIL_NOT_FOUND);
    }
}
