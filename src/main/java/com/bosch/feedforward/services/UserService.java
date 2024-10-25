package com.bosch.feedforward.services;

import com.bosch.feedforward.dto.LoginResponseDTO;
import com.bosch.feedforward.dto.PasswordDTO;
import com.bosch.feedforward.entity.UserEntity;
import com.bosch.feedforward.exceptions.UserNotFoundException;

import java.util.UUID;

public interface UserService {

    UserEntity registerUser(UserEntity user) throws UserNotFoundException;

    LoginResponseDTO loginUser(UserEntity user);

    String firstLogin(UUID id, PasswordDTO data);
}
