package com.bosch.feedforward.repository;

import com.bosch.feedforward.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeriodRepository extends JpaRepository<Period, UUID> {
}
