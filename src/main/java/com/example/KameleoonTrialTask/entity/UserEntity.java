package com.example.KameleoonTrialTask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @ToString.Exclude
    private String password;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime dataCreated;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<QuoteEntity> quotes;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<VoteEntity> votes;

}
