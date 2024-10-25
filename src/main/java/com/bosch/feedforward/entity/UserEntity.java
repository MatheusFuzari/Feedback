package com.bosch.feedforward.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Entity
@Table(name = "user")
@Data
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    Set<Role> roles = new HashSet<>();

    @ColumnDefault("false")
    private Boolean is_active;
}
