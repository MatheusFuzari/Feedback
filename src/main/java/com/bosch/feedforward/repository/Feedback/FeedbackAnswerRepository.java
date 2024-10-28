package com.bosch.feedforward.repository.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackAnswer;
import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import com.bosch.feedforward.entity.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeedbackAnswerRepository extends JpaRepository<FeedbackAnswer, UUID>, JpaSpecificationExecutor<FeedbackAnswer> {

    List<FeedbackAnswer> findAll(Specification<FeedbackAnswer> spec);

    Page<FeedbackAnswer> findAll(Specification<FeedbackAnswer> spec, Pageable page);

    @Query("""
            SELECT fa.* FROM 
            feedback_answer AS fa 
            JOIN
            feedback AS f 
            ON fa.feedback_id=f.id
            """)
    List<FeedbackAnswer> findAllByFeedbackId(UUID id);
}
