package com.bosch.feedforward.repository.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackAnswerRepository extends JpaRepository<FeedbackAnswer, UUID> {
}
