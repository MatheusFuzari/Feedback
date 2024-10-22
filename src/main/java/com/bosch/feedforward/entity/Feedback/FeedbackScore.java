package com.bosch.feedforward.entity.Feedback;

import com.bosch.feedforward.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class FeedbackScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Feedback feedback;

    @NotBlank
    @Column(nullable = false,)
    private Float mean;

    @NotBlank
    private UserEntity user;
}
