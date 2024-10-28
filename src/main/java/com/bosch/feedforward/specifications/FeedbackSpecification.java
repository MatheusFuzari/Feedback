package com.bosch.feedforward.specifications;

import com.bosch.feedforward.entity.Feedback.Feedback;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.True;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class FeedbackSpecification {

    @And({
            @Spec(path = "id", params = "feedbackId", spec = Equal.class),
            @Spec(path = "user.id", params = "userId", spec = Equal.class),
            @Spec(path = "semester.name", params = "semester", spec = EqualIgnoreCase.class),
            @Spec(path = "open", params = "open", spec = True.class)
    })
    public static interface FeedbackSpec extends Specification<Feedback>{}
}
