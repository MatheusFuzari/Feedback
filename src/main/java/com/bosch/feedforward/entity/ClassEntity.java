package com.bosch.feedforward.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "class")
@Entity
@Data
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Course course;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Column(nullable = false)
    private UserEntity godparent;

    @NotBlank
    @Column(nullable = false)
    private String color;

    @NotBlank
    @Column(nullable = false)
    private int apprenticeQuantity;

    @NotBlank
    @Column(nullable = false)
    private int periodQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(columnDefinition = "boolean default false")
    private boolean isGraduated;
}
