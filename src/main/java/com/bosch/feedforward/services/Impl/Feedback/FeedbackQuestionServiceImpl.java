package com.bosch.feedforward.services.Impl.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import com.bosch.feedforward.repository.Feedback.FeedbackQuestionRepository;
import com.bosch.feedforward.services.Feedback.FeedbackQuestionService;
import com.bosch.feedforward.services.Feedback.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackQuestionServiceImpl implements FeedbackQuestionService {

    @Autowired
    private FeedbackQuestionRepository feedbackQuestionRepository;

    @Override
    public FeedbackQuestion getQuestionById(UUID id) {
        Optional<FeedbackQuestion> feedbackQuestionFound = this.feedbackQuestionRepository.findById(id);

        if(feedbackQuestionFound.isPresent()){
            return feedbackQuestionFound.get();
        }

        return null;
    }

    @Override
    public Page<FeedbackQuestion> getQuestions(Specification<FeedbackQuestion> spec, Pageable page) {
        return this.feedbackQuestionRepository.findAll(spec, page);
    }

    @Override
    public List<FeedbackQuestion> createQuestions(List<FeedbackQuestion> questionList) {
        return this.feedbackQuestionRepository.saveAllAndFlush(questionList);
    }

    @Override
    public FeedbackQuestion updateQuestion(UUID id, FeedbackQuestion feedbackQuestion) {
        Optional<FeedbackQuestion> feedbackQuestionFound = this.feedbackQuestionRepository.findById(id);

        if(feedbackQuestionFound.isPresent()){
            BeanUtils.copyProperties(feedbackQuestion, feedbackQuestionFound.get());
            return this.feedbackQuestionRepository.saveAndFlush(feedbackQuestionFound.get());
        }

        return null;
    }

    @Override
    public void deleteQuestion(UUID id) {
        Optional<FeedbackQuestion> feedbackQuestionFound = this.feedbackQuestionRepository.findById(id);

        if(feedbackQuestionFound.isPresent()){
            this.feedbackQuestionRepository.delete(feedbackQuestionFound.get());
        }
    }
}
