package com.revolut.notes.service;

import com.revolut.notes.client.PersonServiceFeignClient;
import com.revolut.notes.domain.Note;
import com.revolut.notes.dto.NoteDto;
import com.revolut.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final PersonServiceFeignClient client;

    public List<NoteDto> getAllWithPersons() {
        return noteRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    private NoteDto convertToDto(Note note) {
        var person = client.getById(note.getPersonId());
        return new NoteDto(note.getId(), note.getBody(), person);
    }

}