package domain.db;

import domain.model.Person;

import java.util.List;

public class PersonDbInMemory {
    private PersonService db = new PersonService();

    public PersonDbInMemory() {

    }

    public Person get(String personId) {
        return db.get(personId);
    }

    public List<Person> getAll() {
        return db.getAll();
    }

    public void add(Person person) {
        db.add(person);
    }

    public void update(Person person) {
        db.update(person);
    }

    public void delete(String id) {
        db.delete(id);
    }
}
