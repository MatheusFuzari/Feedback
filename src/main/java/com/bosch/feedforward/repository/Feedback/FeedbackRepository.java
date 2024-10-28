package com.bosch.feedforward.repository.Feedback;

import com.bosch.feedforward.entity.Feedback.Feedback;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID>, JpaSpecificationExecutor<Feedback> {

    List<Feedback> findAll(Specification<Feedback> spec);

    Page<Feedback> findAll(Specification<Feedback> spec, Pageable page);
}
