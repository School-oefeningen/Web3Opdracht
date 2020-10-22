package domain.service;

import domain.db.ContactDBSQL;
import domain.db.ContactDb;
import domain.model.Contact;
import domain.model.DomainException;
import util.Checker;

import java.util.List;

public class ContactService {
    private final ContactDb db = new ContactDBSQL();

    public ContactService() {
    }

    public List<Contact> getAll(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        return db.getAll(userId);
    }

    public void add(Contact contact) {
        // Exception if contact is null
        if (contact == null) throw new DomainException("No contact given");

        // Add contact
        db.add(contact);
    }
}