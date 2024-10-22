package com.bosch.feedforward.dto;

import com.bosch.feedforward.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String edv;

    private String password;

    List<UUID> roles;
}
