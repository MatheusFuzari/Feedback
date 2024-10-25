package com.bosch.feedforward.services.Feedback;

import com.bosch.feedforward.entity.Feedback.FeedbackCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FeedbackCategoryService {

    FeedbackCategory getCategoryById(UUID id);

    Page<FeedbackCategory> getFeedbackCategories(Pageable page);

    List<FeedbackCategory> createCategories(List<FeedbackCategory> categoryList);

    FeedbackCategory updateCategory(UUID id, FeedbackCategory feedbackCategory);

    void deleteCategory(UUID id);
}
