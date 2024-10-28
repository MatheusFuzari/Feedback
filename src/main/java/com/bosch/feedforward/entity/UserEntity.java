package com.bosch.feedforward.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String lastName;

    private String email;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true, length = 8)
    private String edv;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = true)
    private String password;


    @Column(nullable = false)
    Set<Roles> roles = new HashSet<>();

    @ColumnDefault("false")
    private Boolean is_active;
}
