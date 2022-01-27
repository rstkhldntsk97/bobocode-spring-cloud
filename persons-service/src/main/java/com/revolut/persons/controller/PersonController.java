package com.revolut.persons.controller;

import com.revolut.persons.domain.Person;
import com.revolut.persons.dto.PersonDto;
import com.revolut.persons.repository.PersonRepository;
import com.revolut.persons.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;
    private final PersonService service;

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return repository.findAll();
    }

    @GetMapping("/getPersonWithNotes/{personId}")
    public PersonDto getPersonWithNotes(@PathVariable Long personId) {
        return service.getWithNotesById(personId);
    }

    @GetMapping("/{id}")
    public Person getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

}
