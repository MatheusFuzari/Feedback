package com.bosch.feedforward.entity.Feedback;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class FeedbackQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @NotNull
    @Column(length = 1000, nullable = false, unique = true)
    private String question;

    @NotBlank
    @NotNull
    @Column(length = 1000, nullable = false, unique = true)
    private String subText;

    @NotBlank
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FeedbackCategory feedbackCategory;
}
