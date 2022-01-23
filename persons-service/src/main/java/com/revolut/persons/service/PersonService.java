package com.revolut.persons.service;

import com.revolut.persons.client.NoteClient;
import com.revolut.persons.domain.Person;
import com.revolut.persons.dto.NoteDto;
import com.revolut.persons.dto.PersonDto;
import com.revolut.persons.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final NoteClient noteClient;

    public PersonDto getWithNotesById(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow();
        List<NoteDto> personNotes = noteClient.getNoteByPersonId(personId);
        return new PersonDto(person.getFirstName(), person.getLastName(), personNotes);
    }
}
