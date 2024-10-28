package com.bosch.feedforward.services;

import com.bosch.feedforward.entity.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SemesterService {

    Semester getSemesterById(UUID id);

    Page<Semester> getAllSemesters(Pageable page);

    List<Semester> createSemesters(List<Semester> semesterList);

}
