package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person person) {
        Person personInDatabase = repository.save(person);
        return personInDatabase;
    }

    public Person readById(Long id) {
        return repository.findById(id).get();
    }

    public List<Person> readAll() {
        Iterable<Person> iterable = repository.findAll();
        List<Person> personList = new ArrayList<>();
        iterable.forEach(personList::add);
        return personList;
    }

    public Person update(Long idOfPersonToUpdate, Person newDataToPersist) {
        Person personInDatabase = this.readById(idOfPersonToUpdate);
        personInDatabase.setFirstName(newDataToPersist.getFirstName());
        personInDatabase.setLastName(newDataToPersist.getLastName());
        personInDatabase = repository.save(personInDatabase);
        return personInDatabase;
    }

    public Person delete(Long idPersonToDelete) {
        Person personToDelete = this.readById(idPersonToDelete);
        repository.delete(personToDelete);
        return personToDelete;
    }
}
