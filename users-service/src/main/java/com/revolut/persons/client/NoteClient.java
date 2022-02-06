package com.revolut.persons.client;

import com.revolut.persons.dto.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notes-server")
public interface NoteClient {

    @GetMapping("/notes/{userId}")
    List<NoteDto> getNoteByUserId(@PathVariable Long userId);

}
