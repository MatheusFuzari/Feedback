package com.bosch.feedforward.services.Impl.Feedback;

import com.bosch.feedforward.entity.Feedback.Feedback;
import com.bosch.feedforward.repository.Feedback.FeedbackRepository;
import com.bosch.feedforward.services.Feedback.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback getFeedbackById(UUID id) {
        Optional<Feedback> feedbackFound = this.feedbackRepository.findById(id);

        if(feedbackFound.isPresent()){
            return feedbackFound.get();
        }

        return null;
    }

    @Override
    public Page<Feedback> getAllFeedbacks(Specification<Feedback> spec, Pageable page) {
        return this.feedbackRepository.findAll(spec, page);
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return this.feedbackRepository.saveAndFlush(feedback);
    }

    @Override
    public Feedback updateFeedback(UUID id, Feedback feedback) {
        Optional<Feedback> feedbackFound = this.feedbackRepository.findById(id);

        if(feedbackFound.isPresent()){
            BeanUtils.copyProperties(feedback, feedbackFound.get());
            return this.feedbackRepository.saveAndFlush(feedbackFound.get());
        }

        return null;
    }

    @Override
    public void deleteFeedback(UUID id) {
        Optional<Feedback> feedbackFound = this.feedbackRepository.findById(id);

        if(feedbackFound.isPresent()){
            this.feedbackRepository.delete(feedbackFound.get());
        }

    }
}
