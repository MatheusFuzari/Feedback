package com.bosch.feedforward.services;

import com.bosch.feedforward.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    Course getCourseById(UUID id);

    Page<Course> getAllCourses(Pageable page);

    List<Course> createCourses(List<Course> courses);
}
