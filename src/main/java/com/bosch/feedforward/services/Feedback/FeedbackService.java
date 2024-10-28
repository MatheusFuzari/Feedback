package com.bosch.feedforward.services.Feedback;


import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    Feedback getFeedbackById(UUID id);

    Page<Feedback> getAllFeedbacks(Specification<Feedback> spec, Pageable page);

    Feedback createFeedback(Feedback feedback);

    Feedback updateFeedback(UUID id, Feedback feedback);

    void deleteFeedback(UUID id);
}
