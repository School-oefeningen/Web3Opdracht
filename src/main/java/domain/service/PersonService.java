package domain.service;

import domain.db.DbException;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
    private final PersonDB db = new PersonDBSQL();
    private final Map<String, Person> people = new HashMap<>();

    public PersonService() {
    }

    public Person get(String personId) {
        // Exception if personId null
        if (personId == null) {
            throw new DbException("No id given");
        }

        // Update people map
        updatePeopleMap();

        //Exception if personId not in people map
        Person person = people.get(personId.toLowerCase());
        if (person == null) throw new DbException("Person not in database");

        return person;
    }

    public List<Person> getAll() {
        updatePeopleMap();
        return new ArrayList<Person>(people.values());
    }

    public void add(Person person) {
        // Exception if person is null
        if (person == null) {
            throw new DbException("No person given");
        }

        // Update the people map
        updatePeopleMap();

        // Exception if map contains person
        if (people.containsKey(person.getUserid())) {
            throw new DbException("User already exists");
        }

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

    private void updatePeopleMap() {
        people.clear();
        for (Person p: db.getAll()) {
            people.put(p.getUserid(), p);
        }
    }
}
