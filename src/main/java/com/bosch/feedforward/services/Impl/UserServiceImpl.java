package com.bosch.feedforward.services.Impl;

import com.bosch.feedforward.config.security.TokenService;
import com.bosch.feedforward.dto.LoginResponseDTO;
import com.bosch.feedforward.dto.PasswordDTO;
import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.entity.UserEntity;
import com.bosch.feedforward.exceptions.UserNotFoundException;
import com.bosch.feedforward.repository.SemesterRepository;
import com.bosch.feedforward.repository.UserRepository;
import com.bosch.feedforward.services.Impl.Feedback.FeedbackServiceImpl;
import com.bosch.feedforward.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserEntity registerUser(UserEntity user) throws UserNotFoundException {
        Optional<UserEntity> userFound = this.userRepository.findByEdv(user.getEdv());

        if(userFound.isEmpty()){
            if(user.getPassword() != null){
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            }


            UserEntity createdUser = this.userRepository.save(user);

            if(createdUser.getRoles().contains("ROLE_APPRENDICE")){
                Optional<Semester> firstSemester = this.semesterRepository.findByName("Q1");

                this.feedbackService.createFeedback(
                        Feedback.builder()
                                .open(false)
                                .mean(new BigDecimal(0.0))
                                .user(createdUser)
                                .semester(firstSemester.isPresent() ? firstSemester.get() : null)
                                .build()
                );
            }

            return createdUser;
        }

        return null;
    }

    @Override
    public LoginResponseDTO loginUser(UserEntity user) {
        Optional<UserEntity> userFound = this.userRepository.findByEdv(user.getEdv());
        LoginResponseDTO loginResponse = new LoginResponseDTO();

        if(userFound.isPresent() && userFound.get().getPassword() == null && user.getPassword().isBlank()){
            loginResponse.setData(userFound.get().getId().toString());
            loginResponse.setStatus("FIRST_ACCESS");
            return loginResponse;
        }else if(userFound.isPresent() && passwordEncoder.matches(user.getPassword(), userFound.get().getPassword())){
            loginResponse.setData(this.tokenService.generateToken(userFound.get()));
            loginResponse.setStatus("SUCCESS");
            return loginResponse;
        }

        loginResponse.setData("");
        loginResponse.setStatus("ERROR");
        return loginResponse;
    }

    @Override
    public String firstLogin(UUID id, PasswordDTO data) {
        Optional<UserEntity> userFound = this.userRepository.findById(id);

        if(userFound.isPresent() && userFound.get().getPassword() == null && data.getPassword().equals(data.getPassword_confirm())){
            userFound.get().setPassword(this.passwordEncoder.encode(data.getPassword()));
            this.userRepository.save(userFound.get());
            return "Password set with success";
        }

        return null;
    }
}
