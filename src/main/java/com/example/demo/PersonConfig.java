package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {
    @Autowired
    private PersonService service;

    @PostConstruct
    public void setup() {
        service.create(new Person(10L, "Leon", "Hunter"));
    }


}
