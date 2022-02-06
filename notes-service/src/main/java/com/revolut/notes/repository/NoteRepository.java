package com.revolut.notes.repository;

import com.revolut.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByPersonId(Long personId);

}
