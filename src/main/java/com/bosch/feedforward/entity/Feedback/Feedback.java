package com.bosch.feedforward.entity.Feedback;

import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Semester semester;

    @NotBlank
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity user;

    @NotBlank
    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean open;

    @Column(precision = 3, scale = 2)
    private BigDecimal mean;
}
