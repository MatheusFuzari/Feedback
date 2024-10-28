package com.bosch.feedforward.dto;

import com.bosch.feedforward.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
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

    private List<String> roles;

    @JsonIgnore
    public Set<Roles> mapToRoles(){
        Set<Roles> rolesList = new HashSet<>();

        for (String roleName: roles){
            Roles.valueOf(roleName);
        };

        return rolesList;
    }
}
