//package com.revolut.persons.domain;
//
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Objects;
//
//@Entity
//@Table(name = "users")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class User {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @NotNull(message = "First name cannot be empty")
//    @Column(name = "first_name", nullable = false)
//    @Size(min = 2, message = "First name must be longer than 2 characters")
//    private String firstName;
//
//    @NotNull(message = "Last name cannot be empty")
//    @Size(min = 2, message = "Last name must be longer than 2 characters")
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//
//    @NotNull(message = "Password cannot be empty")
//    @Size(min = 8, max = 16, message = "Passwords doesn't meet the requirements")
//    @Column(nullable = false)
//    private String password;
//
//    @NotNull
//    @Column(nullable = false)
//    @Email
//    private String email;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return firstName.equals(user.firstName) && lastName.equals(user.lastName) && password.equals(user.password) && email.equals(user.email);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, lastName, password, email);
//    }
//
//}



