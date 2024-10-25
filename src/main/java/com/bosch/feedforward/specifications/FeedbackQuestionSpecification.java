package com.bosch.feedforward.specifications;

import com.bosch.feedforward.entity.Feedback.FeedbackQuestion;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class FeedbackQuestionSpecification {

    @And({
            @Spec(path = "feedbackCategory.id", params = "categoryId", spec = Equal.class)
    })
    public static interface QuestionSpec extends Specification<FeedbackQuestion>{}
}
