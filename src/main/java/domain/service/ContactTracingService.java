package domain.service;

public class ContactTracingService {
    private final PersonService personService = new PersonService();
    private final ContactService contactService = new ContactService();
    private final TestResultService testResultService = new TestResultService();

    public ContactTracingService() {}

    public PersonService getPersonService() {
        return personService;
    }

    public ContactService getContactService() {
        return contactService;
    }

    public TestResultService getTestResultService() {
        return testResultService;
    }

    public void deleteAccount(String userId) {
        personService.remove(userId);
        contactService.removeFromUser(userId);
        testResultService.removeFromUser(userId);
    }
}
