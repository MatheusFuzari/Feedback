package com.bosch.feedforward.entity.Feedback;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class FeedbackQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(length = 1000, nullable = false, unique = true)
    private String question;

    @NotBlank
    @Column(length = 1000, nullable = false, unique = true)
    private String subText;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FeedbackCategory feedbackCategory;
}
