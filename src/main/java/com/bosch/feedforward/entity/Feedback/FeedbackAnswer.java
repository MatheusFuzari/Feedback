package com.bosch.feedforward.entity.Feedback;

import com.bosch.feedforward.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Feedback feedback;

    @NotNull
    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FeedbackQuestion feedbackQuestion;

    @NotNull
    @NotBlank
    private int answer;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @CreationTimestamp
    private LocalDateTime responseDate;

    @NotNull
    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity respondent;

}
