package com.bosch.feedforward.services;

import com.bosch.feedforward.entity.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PeriodService {

    Period getPeriodById(UUID id);

    Page<Period> getAllPeriods(Pageable page);

    List<Period> createPeriods();
}
