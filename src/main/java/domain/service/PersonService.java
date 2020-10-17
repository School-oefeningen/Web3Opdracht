package domain.service;

import domain.db.DbException;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.List;

public class PersonService {
    private final PersonDB db = new PersonDBSQL();

    public PersonService() {
    }

    public Person get(String personId) {
        // Exception if personId null
        if (personId == null) {
            throw new DbException("No id given");
        }

        //Exception if person not in db
        if (!db.personInDb(personId.toLowerCase())) throw new DbException("Person not in database");

        return db.get(personId);
    }

    public List<Person> getAll() {
        return db.getAll();
    }

    public void add(Person person) {
        // Exception if person is null
        if (person == null) {
            throw new DbException("No person given");
        }

        //Exception if person in db
        if (db.personInDb(person.getUserid().toLowerCase())) throw new DbException("Person already in database");

        // Add person
        db.add(person);
    }

    public void update(Person person) {
        // Exception if person is null
        if (person == null) {
            throw new DbException("No person given");
        }

        delete(person.getUserid());
        add(person);
    }

    public void delete(String personId) {
        // Exception if personId is null
        if (personId == null || personId.trim().isEmpty()) {
            throw new DbException("No id given");
        }

        // Remove person in db
        db.remove(personId);
    }

    public int getNumberOfPersons() {
        return db.getAll().size();
    }
}
