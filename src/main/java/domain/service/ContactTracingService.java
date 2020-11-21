package domain.service;

import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;

import java.util.List;

public class ContactTracingService {
    private final PersonService personService = new PersonService();
    private final ContactService contactService = new ContactService();
    private final TestResultService testResultService = new TestResultService();

    public ContactTracingService() {
    }

    public Person getPerson(String personId) {
        return personService.get(personId.toLowerCase());
    }

    public List<Person> getAllPersons() {
        return personService.getAll();
    }

    public void addPerson(Person person) {
        personService.add(person);
    }

    public void updatePerson(Person person) {
        personService.update(person);
    }

    public void removePerson(String personId) {
        personService.remove(personId);
    }

    public List<Contact> getAllContactsFromUser(String userId) {
        return contactService.getAllFromUser(userId);
    }

    public List<Contact> getAllContacts() {
        return contactService.getAll();
    }

    public void addContact(Contact contact) {
        contactService.add(contact);
    }

    public List<Contact> getAllContactFromUserAfterDate(TestResult testResult) {
        return contactService.getAllFromUserAfterDate(testResult);
    }

    public void removeContactsFromUser(String userId) {
        contactService.removeFromUser(userId);
    }

    public TestResult getTestResultFromUser(String userId) {
        return testResultService.getTestResultFromUser(userId);
    }

    public List<TestResult> getAllTestResults() {
        return testResultService.getAll();
    }

    public void addTestResult(TestResult testResult) {
        testResultService.add(testResult);
    }

    public void removeTestResultFromUser(String userId) {
        testResultService.removeFromUser(userId);
    }
}
