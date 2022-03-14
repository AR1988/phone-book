package de.reutow.phonebook.service;

import de.reutow.phonebook.entity.Contact;
import de.reutow.phonebook.entity.Phone;
import de.reutow.phonebook.exceptions.ContactNotFoundException;
import de.reutow.phonebook.exceptions.PhoneNotFoundException;
import de.reutow.phonebook.repo.IContactRepo;
import de.reutow.phonebook.repo.IPhoneRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    private static final String PHONE_NOT_FOUND = "phone is not found";
    private static final String CONTACT_NOT_FOUND = "required contact is not found";
    private IPhoneRepo phoneRepo;
    private IContactRepo contactRepo;

    public PhoneService(IPhoneRepo phoneRepo, IContactRepo contactRepo) {
        this.phoneRepo = phoneRepo;
        this.contactRepo = contactRepo;
    }

    public List<Phone> getAllByContactId(long contactId) {
        if (!contactRepo.existsById(contactId))
            throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        return phoneRepo.findAllByContactId(contactId);
    }

    public Phone getById(long phoneId) {
        return phoneRepo.findById(phoneId)
                .orElseThrow(() -> new PhoneNotFoundException(PHONE_NOT_FOUND));
    }

    public Phone addPhone(String countryCode, String telephoneNumber, boolean isFavorite, long contactId) {
        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_NOT_FOUND));
        Phone newPhone = new Phone(countryCode, telephoneNumber, isFavorite, contact);
        return phoneRepo.save(newPhone);
    }

    public void editById(String countryCode, String telephoneNumber, boolean isFavorite, long phoneId) {
        Phone toEdit = phoneRepo.findById(phoneId)
                .orElseThrow(() -> new PhoneNotFoundException(PHONE_NOT_FOUND));
        toEdit.setCountryCode(countryCode);
        toEdit.setTelephoneNumber(telephoneNumber);
        toEdit.setFavorite(isFavorite);
        phoneRepo.save(toEdit);
    }

    public void deleteById(long phoneId) {
        if (phoneRepo.existsById(phoneId))
            phoneRepo.deleteById(phoneId);
        else
            throw new PhoneNotFoundException(PHONE_NOT_FOUND);
    }
}
