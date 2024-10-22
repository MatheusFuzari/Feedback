package com.bosch.feedforward.services.Impl;

import com.bosch.feedforward.config.security.TokenService;
import com.bosch.feedforward.dto.TokenDTO;
import com.bosch.feedforward.entity.UserEntity;
import com.bosch.feedforward.repository.UserRepository;
import com.bosch.feedforward.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(UserEntity user) {
        Optional<UserEntity> userFound = this.userRepository.findByEdv(user.getEdv());

        if(userFound.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return this.userRepository.save(user);
        }

        return null;
    }

    @Override
    public TokenDTO loginUser(UserEntity user) {
        Optional<UserEntity> userFound = this.userRepository.findByEdv(user.getEdv());

        if(userFound.isPresent() && passwordEncoder.matches(user.getPassword(), userFound.get().getPassword())){
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(this.tokenService.generateToken(userFound.get()));
            tokenDTO.setDate(LocalDateTime.now());
            return tokenDTO;
        }

        return null;
    }
}
