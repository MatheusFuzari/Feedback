package com.bosch.feedforward.services;

import com.bosch.feedforward.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role getRoleById(UUID id);

    Page<Role> getAllRoles(Pageable page);
    List<Role> createRoles(List<Role> roles);

    void deleteRole(Role role);

}
