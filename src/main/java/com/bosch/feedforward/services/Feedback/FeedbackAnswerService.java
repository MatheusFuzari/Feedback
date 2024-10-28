package com.bosch.feedforward.services.Feedback;

import com.bosch.feedforward.dto.Feedback.FeedbackAnswerDTO;
import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.entity.Feedback.FeedbackAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface FeedbackAnswerService {
    FeedbackAnswer getAnswerById(UUID id);

    Page<FeedbackAnswer> getAllAnswers(Specification<FeedbackAnswer> spec, Pageable page);

    List<FeedbackAnswer> createAnswer(FeedbackAnswerDTO answer);

    List<FeedbackAnswer> updateAnswer(UUID id, FeedbackAnswerDTO answer);

    void deleteAnswer(UUID id);
}
