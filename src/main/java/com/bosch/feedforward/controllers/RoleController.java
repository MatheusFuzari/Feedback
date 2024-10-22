package com.bosch.feedforward.controllers;


import com.bosch.feedforward.dto.RoleDTO;
import com.bosch.feedforward.entity.Role;
import com.bosch.feedforward.services.Impl.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/roles")
    public ResponseEntity<Page<Role>> getAllRoles(Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.getAllRoles(page));
    }

    @PostMapping("/roles")
    public ResponseEntity<List<Role>> createRoles(@RequestBody @Valid List<RoleDTO> roles){
        List<Role> roleList = roles.stream().map(
                role -> {
                    Role convertedRole = new Role();
                    BeanUtils.copyProperties(role, convertedRole);
                    return convertedRole;
                }
        ).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.createRoles(roleList));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable UUID id){
        Role convertedRole = new Role();
        convertedRole.setId(id);
        this.roleService.deleteRole(convertedRole);
    }

}
