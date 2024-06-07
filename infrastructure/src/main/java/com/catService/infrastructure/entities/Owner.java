package com.catService.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Owner {

    @Id
    @SequenceGenerator(
            name = "owner_seq_gen",
            sequenceName = "owner_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq_gen")
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Cat> cats = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String username;

    public Owner(Long id, String name, LocalDate birthDate, String username) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.username = username;
    }

    public Owner(String name, LocalDate birthDate, String username, User user) {
        this.name = name;
        this.birthDate = birthDate;
        this.username = username;
        this.user = user;
    }
}
