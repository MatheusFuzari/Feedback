package com.bosch.feedforward.repository.Feedback;

import com.bosch.feedforward.entity.Feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
