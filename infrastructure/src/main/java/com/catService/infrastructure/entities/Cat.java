package com.catService.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cat {

    @Id
    @SequenceGenerator(
            name = "cat_seq_gen",
            sequenceName = "cat_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq_gen")
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Cat_Friend",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_friend_id")
    )
    @JsonIgnore
    private Set<Cat> friends = new HashSet<>();

    public Cat(Long id, String name, LocalDate birthDate, String breed, Color color) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
