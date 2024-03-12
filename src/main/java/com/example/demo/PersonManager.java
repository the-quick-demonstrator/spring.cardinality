package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PersonManager {
    @Id
    @GeneratedValue
    Long id;

    @OneToOne
    private Person person;
}
