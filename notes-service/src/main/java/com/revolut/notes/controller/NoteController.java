package com.revolut.notes.controller;

import com.revolut.notes.domain.Note;
import com.revolut.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository repository;

    @GetMapping
    public List<Note> getNoteByPersonId(@RequestParam Long personId) {
        return repository.findAllByPersonId(personId);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return repository.save(note);
    }

}
