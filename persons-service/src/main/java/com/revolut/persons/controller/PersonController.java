package com.revolut.persons.controller;

import com.revolut.persons.domain.Person;
import com.revolut.persons.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return repository.findAll();
    }

}
