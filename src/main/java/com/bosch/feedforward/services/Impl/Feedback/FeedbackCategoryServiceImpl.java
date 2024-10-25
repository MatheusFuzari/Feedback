package com.bosch.feedforward.services.Impl.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackCategory;
import com.bosch.feedforward.repository.Feedback.FeedbackCategoryRepository;
import com.bosch.feedforward.services.Feedback.FeedbackCategoryService;
import com.bosch.feedforward.services.Feedback.FeedbackQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackCategoryServiceImpl implements FeedbackCategoryService {

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    @Override
    public FeedbackCategory getCategoryById(UUID id) {
        Optional<FeedbackCategory> feedbackCategoryFound = this.feedbackCategoryRepository.findById(id);

        if(feedbackCategoryFound.isPresent()){
            return feedbackCategoryFound.get();
        }

        return null;
    }

    @Override
    public Page<FeedbackCategory> getFeedbackCategories(Pageable page) {
        return this.feedbackCategoryRepository.findAll(page);
    }

    @Override
    public List<FeedbackCategory> createCategories(List<FeedbackCategory> categoryList) {
        return this.feedbackCategoryRepository.saveAllAndFlush(categoryList);
    }

    @Override
    public FeedbackCategory updateCategory(UUID id, FeedbackCategory feedbackCategory) {
        Optional<FeedbackCategory> feedbackCategoryFound = this.feedbackCategoryRepository.findById(id);

        if(feedbackCategoryFound.isPresent()){
            BeanUtils.copyProperties(feedbackCategory, feedbackCategoryFound.get());
            return this.feedbackCategoryRepository.saveAndFlush(feedbackCategoryFound.get());
        }

        return null;
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<FeedbackCategory> feedbackCategoryFound = this.feedbackCategoryRepository.findById(id);

        if(feedbackCategoryFound.isPresent()){
            this.feedbackCategoryRepository.delete(feedbackCategoryFound.get());
        }
    }
}
