package com.bosch.feedforward.services.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface FeedbackQuestionService {

    FeedbackQuestion getQuestionById(UUID id);

    Page<FeedbackQuestion> getQuestions(Specification<FeedbackQuestion> spec, Pageable page);

    List<FeedbackQuestion> createQuestions(List<FeedbackQuestion> questionList);

    FeedbackQuestion updateQuestion(UUID id, FeedbackQuestion feedbackQuestion);

    void deleteQuestion(UUID id);
}
