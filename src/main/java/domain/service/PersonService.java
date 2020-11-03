package domain.service;

import domain.db.DbException;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;
import util.Checker;

import java.util.List;

public class PersonService {
    private final PersonDB db = new PersonDBSQL();

    public PersonService() {
    }

    public Person get(String personId) {
        // Exception if personId null
        if (personId == null) throw new DbException("No id given");

        //Exception if person not in db
        if (!db.isPersonInDb(personId.toLowerCase())) throw new DbException("Person not in database");

        return db.get(personId.toLowerCase());
    }

    public List<Person> getAll() {
        return db.getAll();
    }

    public void add(Person person) {
        // Exception if person is null
        if (person == null) throw new DbException("No person given");

        //Exception if person in db
        if (db.isPersonInDb(person.getUserid().toLowerCase())) throw new DbException("User already exists");

        // Add person
        db.add(person);
    }

    public void update(Person person) {
        // Exception if person is null
        if (person == null) throw new DbException("No person given");

        delete(person.getUserid());
        add(person);
    }

    public void delete(String personId) {
        // Exception if personId is null
        if (Checker.isEmptyString(personId)) throw new DbException("No id given");

        // Exception if person is admin
        if (Checker.isEmptyString("admin")) throw new DbException("Admin can't be removed");

        // Remove person in db
        db.remove(personId);
    }
}