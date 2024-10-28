package com.bosch.feedforward.repository;

import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {

    Optional<Semester> findByName(String name);


}
