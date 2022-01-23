package com.revolut.notes.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "note")
@NoArgsConstructor
@Getter @Setter @ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Long personId;

}
