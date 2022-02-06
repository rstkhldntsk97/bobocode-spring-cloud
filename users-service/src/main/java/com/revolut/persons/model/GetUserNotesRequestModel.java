package com.revolut.persons.model;

import com.revolut.persons.dto.NoteDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetUserNotesRequestModel {
    private String firstName;

    private String lastName;

    private String email;

    private List<NoteDto> notes;
}
