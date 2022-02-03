package com.revolut.persons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6661186831733675824L;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String userId;

    private String encryptedPassword;

}