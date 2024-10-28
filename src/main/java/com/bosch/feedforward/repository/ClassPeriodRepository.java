package com.bosch.feedforward.repository;

import com.bosch.feedforward.entity.ClassSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassPeriodRepository extends JpaRepository<ClassSemester, UUID> {
}
