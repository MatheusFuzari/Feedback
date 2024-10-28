package com.bosch.feedforward.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ClassSemester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ClassEntity classEntity;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Semester semester;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDate;

}
