package com.bosch.feedforward.repository;

import com.bosch.feedforward.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
