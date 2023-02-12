package com.example.KameleoonTrialTask.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.mapstruct.control.MappingControl;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quotes")
public class QuoteEntity {


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "create")
    @CreationTimestamp
    private LocalDateTime dataCreated;

    @OneToMany(mappedBy = "quote", orphanRemoval = true)
    private Set<Vote> votes = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
