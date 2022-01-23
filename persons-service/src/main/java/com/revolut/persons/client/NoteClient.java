package com.revolut.persons.client;

import com.revolut.persons.dto.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "notes")
public interface NoteClient {

    @GetMapping("/notes")
    List<NoteDto> getNoteByPersonId(@RequestParam Long personId);

}
