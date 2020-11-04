package domain.service;

import domain.db.ContactDBSQL;
import domain.db.ContactDb;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.TestResult;
import util.Checker;

import java.util.List;

public class ContactService {
    private final ContactDb db = new ContactDBSQL();

    public ContactService() {
    }

    public List<Contact> getAllFromUser(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        return db.getAllFromUser(userId);
    }

    public List<Contact> getAll() {
        return db.getAll();
    }

    public void add(Contact contact) {
        // Exception if contact is null
        if (contact == null) throw new DomainException("No contact given");

        // Add contact
        db.add(contact);
    }

    public List<Contact> getAllFromUserAfterDate(TestResult testResult) {
        if (testResult == null) throw new DomainException("No test result given");
        return db.getAllFromUserAfterDate(testResult);
    }

    public void removeFromUser(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        db.removeFromUser(userId);
    }
}