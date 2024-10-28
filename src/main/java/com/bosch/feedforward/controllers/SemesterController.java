package com.bosch.feedforward.controllers;

import com.bosch.feedforward.dto.SemesterDTO;
import com.bosch.feedforward.entity.Semester;
import com.bosch.feedforward.services.Impl.SemesterServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class SemesterController {

    @Autowired
    private SemesterServiceImpl semesterService;

    @GetMapping("/semester/{id}")
    private ResponseEntity<Semester> getSemesterById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(this.semesterService.getSemesterById(id));
    }

    @GetMapping("/semester")
    private ResponseEntity<Page<Semester>> getAllSemesters(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(this.semesterService.getAllSemesters(page));
    }

    @PostMapping("/semester")
    private ResponseEntity<List<Semester>> createSemesters(@RequestBody @Valid List<SemesterDTO> data){
        List<Semester> semesterList = data.stream().map(
                dto -> {
                    Semester convertedSemester = new Semester();
                    BeanUtils.copyProperties(dto, convertedSemester);
                    return convertedSemester;
                }
        ).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(this.semesterService.createSemesters(semesterList));
    }
}
