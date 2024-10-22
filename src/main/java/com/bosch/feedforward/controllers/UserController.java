package com.bosch.feedforward.controllers;

import com.bosch.feedforward.dto.TokenDTO;
import com.bosch.feedforward.dto.UserDTO;
import com.bosch.feedforward.dto.UserLoginDTO;
import com.bosch.feedforward.entity.Role;
import com.bosch.feedforward.entity.UserEntity;
import com.bosch.feedforward.services.Impl.RoleServiceImpl;
import com.bosch.feedforward.services.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/login")
    private ResponseEntity<TokenDTO> loginUser(@RequestBody @Valid UserLoginDTO data){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(data, user);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.loginUser(user));
    }

    @PostMapping("/register")
    private ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserDTO data){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(data, user);

        for (UUID roleId: data.getRoles()){
            Role role = this.roleService.getRoleById(roleId);
            user.getRoles().add(role);
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.registerUser(user));
    }
}
