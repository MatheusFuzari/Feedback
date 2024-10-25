package com.bosch.feedforward.dto;

import com.bosch.feedforward.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    private String email;

    @NotBlank
    @NotNull
    private String edv;

    private String password;

    private List<UUID> roles;

    @JsonIgnore
    public Set<Role> mapToRoles(){
        Set<Role> roleIds = new HashSet<>();

        for (UUID roleId: roles){
            roleIds.add(Role.builder()
                    .id(roleId)
                    .build());
        };

        return roleIds;
    }
}
