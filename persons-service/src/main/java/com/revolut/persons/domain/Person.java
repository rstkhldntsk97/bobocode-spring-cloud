package com.revolut.persons.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person")
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@Getter @Setter @ToString
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
