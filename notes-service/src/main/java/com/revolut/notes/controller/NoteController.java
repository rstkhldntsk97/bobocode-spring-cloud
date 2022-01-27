package com.revolut.notes.controller;

import com.revolut.notes.domain.Note;
import com.revolut.notes.dto.NoteDto;
import com.revolut.notes.repository.NoteRepository;
import com.revolut.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository repository;
    private final NoteService noteService;

    @GetMapping("/getNoteByPersonId")
    public List<Note> getNoteByPersonId(@RequestParam Long personId) {
        return repository.findAllByPersonId(personId);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return repository.save(note);
    }

    @GetMapping
    public List<NoteDto> getAll() {
        return noteService.getAllWithPersons();
    }

}
