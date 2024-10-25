package com.bosch.feedforward.repository;

import com.bosch.feedforward.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {
}
