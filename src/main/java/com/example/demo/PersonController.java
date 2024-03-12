package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/person-controller")
public class PersonController {
    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Person> create(@RequestBody Person person) { // I consume a person JSON
        System.out.println("hello world");
        Person newlyCreatedPerson = service.create(person);
        return new ResponseEntity<>(newlyCreatedPerson, HttpStatus.CREATED);
    }

    @GetMapping(value = "/read/{idOfPersonToFind}")
    public ResponseEntity<Person> readById(@PathVariable Long idOfPersonToFind) {
        Person personInDatabase = service.readById(idOfPersonToFind);
        return new ResponseEntity<>(personInDatabase, HttpStatus.OK);
    }

    @GetMapping(value = "/readAll")
    public ResponseEntity<List<Person>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{idOfPersonToUpdate}")
    public ResponseEntity<Person> update(@PathVariable Long idOfPersonToUpdate, @RequestBody Person newPersonData) {
        return new ResponseEntity<>(service.update(idOfPersonToUpdate, newPersonData), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
