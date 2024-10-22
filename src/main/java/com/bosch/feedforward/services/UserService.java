package com.bosch.feedforward.services;

import com.bosch.feedforward.dto.TokenDTO;
import com.bosch.feedforward.dto.UserLoginDTO;
import com.bosch.feedforward.entity.UserEntity;

public interface UserService {

    UserEntity registerUser(UserEntity user);

    TokenDTO loginUser(UserEntity user);
}
