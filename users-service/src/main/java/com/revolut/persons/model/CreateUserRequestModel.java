package com.revolut.persons.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CreateUserRequestModel {

    @NotNull(message = "First name cannot be empty")
    @Size(min = 2, message = "First name must be longer than 2 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 2, message = "Last name must be longer than 2 characters")
    private String lastName;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Passwords doesn't meet the requirements")
    private String password;

    @NotNull
    @Email
    private String email;

}
