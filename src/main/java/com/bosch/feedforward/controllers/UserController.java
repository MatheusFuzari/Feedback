package com.bosch.feedforward.controllers;

import com.bosch.feedforward.dto.LoginResponseDTO;
import com.bosch.feedforward.dto.PasswordDTO;
import com.bosch.feedforward.dto.UserDTO;
import com.bosch.feedforward.dto.UserLoginDTO;
import com.bosch.feedforward.entity.UserEntity;
import com.bosch.feedforward.exceptions.UserNotFoundException;
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

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid UserLoginDTO data){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(data, user);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.loginUser(user));
    }

    @PostMapping("/firstlogin/{id}")
    private ResponseEntity<String> fistLoginUser(@PathVariable UUID id, @RequestBody @Valid PasswordDTO data) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.firstLogin(id, data));
    }

    @PostMapping("/register")
    private ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserDTO data) throws UserNotFoundException {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(data, user);
        user.setRoles(data.mapToRoles());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(user));
    }
}
