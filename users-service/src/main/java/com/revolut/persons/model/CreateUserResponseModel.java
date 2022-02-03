package com.revolut.persons.model;

import lombok.*;

@Getter @Setter @ToString
public class CreateUserResponseModel {

    private String firstName;

    private String lastName;

    private String email;

    private String userId;

}
