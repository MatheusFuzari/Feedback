package com.bosch.feedforward.entity.Feedback;

import com.bosch.feedforward.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

//Talvez não precise dessa tabela, pq :
//é possivel que a função dessa tabela seja atribuida a tabela de Feedback,
//realizando get, post e put, no campo 'mean', onde é possível a cada fila de respostas
//atualizar a média para o atual
@Entity
@Data
public class FeedbackScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Feedback feedback;

    @NotBlank
    @Column(precision = 3, scale = 2)
    private BigDecimal mean;

    @NotBlank
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity user;
}
