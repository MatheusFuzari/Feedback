package com.bosch.feedforward.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity supervisor;
}
