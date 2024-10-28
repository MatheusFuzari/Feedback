package com.bosch.feedforward.services.Impl;

import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.repository.SemesterRepository;
import com.bosch.feedforward.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester getSemesterById(UUID id) {
        Optional<Semester> semesterFound = this.semesterRepository.findById(id);

        if(semesterFound.isPresent()){
            return semesterFound.get();
        }

        return null;
    }

    @Override
    public Page<Semester> getAllSemesters(Pageable page) {
        return this.semesterRepository.findAll(page);
    }

    @Override
    public List<Semester> createSemesters(List<Semester> semesterList) {
        return this.semesterRepository.saveAllAndFlush(semesterList);
    }
}
